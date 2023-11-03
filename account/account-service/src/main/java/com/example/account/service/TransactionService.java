package com.example.account.service;

import com.example.account.api.transaction.*;
import com.example.account.builder.TransactionBuilder;
import com.example.account.comon.AccountErrorsStatic;
import com.example.account.comon.AccountException;
import com.example.account.comon.UtilAccount;
import com.example.account.domain.AccountInfo;
import com.example.account.domain.Person;
import com.example.account.domain.Transaction;
import com.example.account.domain.TransactionInfo;
import com.example.account.helper.Mappers;
import com.example.account.repository.AccountInfoRepository;
import com.example.account.repository.PersonRepository;
import com.example.account.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final PersonRepository personRepository;
    private final AccountInfoRepository accountInfoRepository;

    @Autowired
    private Mappers mappers;

    @Value("${open.account.sign}")
    private String openAccountSign;

    public TransactionService(TransactionRepository transactionRepository,
                              PersonRepository personRepository,
                              AccountInfoRepository accountInfoRepository) {

        this.transactionRepository = transactionRepository;
        this.personRepository = personRepository;
        this.accountInfoRepository = accountInfoRepository;
    }

    public GetAllTransactionsResult getAllTransactions() {
        GetAllTransactionsResult result = new GetAllTransactionsResult();
        List<TransactionResult> transactions = transactionRepository.findAll().stream()
                .map(transaction -> mappers.transactionsMapper(transaction)).collect(Collectors.toList());
        if (transactions.isEmpty() && transactions.size() == 0) {
            throw new AccountException(AccountErrorsStatic.ERROR_TRANSACTION_NOT_FOUND, null);
        }
        result.setItems(transactions);

        return result;
    }

    public GetTransactionsBySourceAccountNumberResult getTransactionsBySourceAccountNumber(GetTransactionsBySourceAccountNumberRequest request) {
        GetTransactionsBySourceAccountNumberResult result = new GetTransactionsBySourceAccountNumberResult();

        List<TransactionResult> transactions = transactionRepository.findTransactionsBySourceAccountNumber(request.getAccountNumber())
                .stream().map(transaction -> mappers.transactionsMapper(transaction)).collect(Collectors.toList());

        if (transactions.isEmpty() && transactions.size() == 0) {
            throw new AccountException(AccountErrorsStatic.ERROR_TRANSACTION_NOT_FOUND, request.getAccountNumber());
        }
        result.setItems(transactions);

        return result;
    }

    public GetTransactionsByDestAccountNumberResult getTransactionsByDestAccountNumber(GetTransactionsByDestAccountNumberRequest request) {
        GetTransactionsByDestAccountNumberResult result = new GetTransactionsByDestAccountNumberResult();

        List<TransactionResult> transactions = transactionRepository.findTransactionsByDestinationAccountNumber(request.getAccountNumber())
                .stream().map(transaction -> mappers.transactionsMapper(transaction)).collect(Collectors.toList());

        if (transactions.isEmpty() && transactions.size() == 0) {
            throw new AccountException(AccountErrorsStatic.ERROR_TRANSACTION_NOT_FOUND, null);
        }
        result.setItems(transactions);

        return result;
    }

    public GetTransactionsByTransferDateResult getTransactionsByTransferDate(GetTransactionsByTransferDateRequest request) {
        GetTransactionsByTransferDateResult result = new GetTransactionsByTransferDateResult();
        List<TransactionResult> transactions = transactionRepository.findTransactionsByTransferDate(request.getTransferDate())
                .stream().map(transaction -> mappers.transactionsMapper(transaction)).collect(Collectors.toList());

        if (transactions.isEmpty() && transactions.size() == 0) {
            throw new AccountException(AccountErrorsStatic.ERROR_TRANSACTION_NOT_FOUND, null);
        }
        result.setItems(transactions);

        return result;
    }


    public InternalTransferResult internalTransfer(InternalTransferRequest request) {
        InternalTransferResult result = new InternalTransferResult();

        AccountInfo sourceAccountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getSourceAccountNumber());
        if (sourceAccountInfo == null) {
            throw new AccountException(AccountErrorsStatic.ERROR_SRC_ACCOUNT_NUMBER_NOT_FOUND, sourceAccountInfo.getAccountNumber());
        }

        Person sourcePerson = personRepository.findPersonByNationalCode(request.getNationalCode())
                .orElseThrow(() -> new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, request.getNationalCode()));

        if (!sourcePerson.getAccountInfo().equals(sourceAccountInfo)) {
            throw new AccountException(AccountErrorsStatic.ERROR_ACCOUNT_DOES_NOT_BELONG_PERSON, request.getSourceAccountNumber(), sourcePerson.getNationalCode());
        }
        if (request.getAmount().compareTo(sourceAccountInfo.getBalance()) == 1) {
            throw new AccountException(AccountErrorsStatic.ERROR_INSUFFICIENT_BALANCE, request.getSourceAccountNumber());
        }

        AccountInfo destAccountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getDestinationAccountNumber());
        if (destAccountInfo == null) {
            throw new AccountException(AccountErrorsStatic.ERROR_DEST_ACCOUNT_INFO_NOT_FOUND, request.getDestinationAccountNumber());
        }

        Person destinationPerson = personRepository.findPersonByAccountInfo(destAccountInfo.getAccountNumber());
        if (destinationPerson == null) {
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_WITH_ACCOUNT_NUMBER_EXIST, destAccountInfo.getAccountNumber());
        }

        sourceAccountInfo.setAmount(request.getAmount());
        sourceAccountInfo.setBalance(sourceAccountInfo.getBalance().add(request.getAmount().negate()));
        sourceAccountInfo.setTransferTypeCode(TransferType.WITHDRAW.code);

        destAccountInfo.setAmount(request.getAmount());
        destAccountInfo.setBalance(destAccountInfo.getBalance().add(request.getAmount()));
        destAccountInfo.setTransferTypeCode(TransferType.DEPOSIT.code);

        accountInfoRepository.save(sourceAccountInfo);
        accountInfoRepository.save(destAccountInfo);

        Transaction transaction = TransactionBuilder.getInstance()
                .sourceAccountNumber(sourceAccountInfo.getAccountNumber())
                .destinationAccountNumber(destAccountInfo.getAccountNumber())
                .amount(request.getAmount())
                .srcBalance(sourceAccountInfo.getBalance())
                .destBalance(destAccountInfo.getBalance())
                .trackingCode(UtilAccount.generateTrackingCode())
                .transferDate(new Date())
                .transactionTypeCode(TransactionType.INTERNAL.code)
                .srcTransferTypeCode(sourceAccountInfo.getTransferTypeCode())
                .build();
        transactionRepository.save(transaction);

        result.setTrackingCode(transaction.getTrackingCode());
        return result;
    }

    public CashDepositResult cashDeposit(CashDepositRequest request) {
        CashDepositResult result = new CashDepositResult();

        AccountInfo destAccountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getAccountNumber());
        if (destAccountInfo == null) {
            throw new AccountException(AccountErrorsStatic.ERROR_DEST_ACCOUNT_INFO_NOT_FOUND);
        }

        Person destPerson = personRepository.findPersonByAccountInfo(destAccountInfo.getAccountNumber());
        if (destPerson == null) {
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_WITH_ACCOUNT_NUMBER_NOT_FOUND, destAccountInfo.getAccountNumber());
        }
        if (request.getAmount() == null) {
            throw new AccountException(AccountErrorsStatic.ERROR_AMOUNT_CAN_NOT_BE_ZERO, request.getAmount());
        }

        destAccountInfo.setAmount(request.getAmount());
        destAccountInfo.setTransferTypeCode(TransferType.DEPOSIT.code);
        destAccountInfo.setBalance(destAccountInfo.getBalance().add(request.getAmount()));
        accountInfoRepository.save(destAccountInfo);

        Transaction transaction = TransactionBuilder.getInstance()
                .sourceAccountNumber(null)
                .destinationAccountNumber(request.getAccountNumber())
                .amount(destAccountInfo.getAmount())
                .srcBalance(null)
                .destBalance(destAccountInfo.getBalance())
                .trackingCode(UtilAccount.generateTrackingCode())
                .transferDate(new Date())
                .transactionTypeCode(TransactionType.CASH.code)
                .srcTransferTypeCode(null)
                .build();

        transactionRepository.save(transaction);
        result.setTrackingCode(transaction.getTrackingCode());

        return result;
    }

    public CashWithdrawResult cashWithdraw(CashWithdrawRequest request) {
        CashWithdrawResult result = new CashWithdrawResult();

        AccountInfo sourceAccountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getAccountNumber());
        if (sourceAccountInfo == null) {
            throw new AccountException(AccountErrorsStatic.ERROR_SRC_ACCOUNT_NUMBER_NOT_FOUND, request.getAccountNumber());
        }

        Person existPerson = personRepository.findPersonByNationalCode(request.getNationalCode())
                .orElseThrow(() -> new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, request.getNationalCode()));

        if (!existPerson.getAccountInfo().equals(sourceAccountInfo)) {
            throw new AccountException(AccountErrorsStatic.ERROR_ACCOUNT_DOES_NOT_BELONG_PERSON, sourceAccountInfo.getAccountNumber(), existPerson.getNationalCode());
        }
        if (request.getAmount().compareTo(sourceAccountInfo.getBalance()) == 1) {
            throw new AccountException(AccountErrorsStatic.ERROR_INSUFFICIENT_BALANCE, sourceAccountInfo.getBalance());
        }

        sourceAccountInfo.setAmount(request.getAmount());
        sourceAccountInfo.setBalance(sourceAccountInfo.getBalance().add(request.getAmount().negate()));
        sourceAccountInfo.setTransferTypeCode(TransferType.WITHDRAW.code);

        accountInfoRepository.save(sourceAccountInfo);

        Transaction transaction = TransactionBuilder.getInstance()
                .srcBalance(sourceAccountInfo.getBalance())
                .amount(sourceAccountInfo.getAmount())
                .destBalance(null)
                .sourceAccountNumber(sourceAccountInfo.getAccountNumber())
                .destinationAccountNumber(null)
                .transactionTypeCode(TransactionType.CASH.code)
                .srcTransferTypeCode(sourceAccountInfo.getTransferTypeCode())
                .trackingCode(UtilAccount.generateTrackingCode())
                .transferDate(new Date())
                .build();

        transactionRepository.save(transaction);
        result.setTrackingCode(transaction.getTrackingCode());

        return result;
    }

    public GetOpenAccountTransactionsResult getOpenAccountTransactions(GetOpenAccountTransactionsRequest request) {
        GetOpenAccountTransactionsResult result = new GetOpenAccountTransactionsResult();
        List<TransactionInfo> customTransactions = transactionRepository.getOpenAccountTransactions(openAccountSign);

        List<GetOpenAccountTransactionsResultItem> items = customTransactions.stream().map(c -> {
            GetOpenAccountTransactionsResultItem item = new GetOpenAccountTransactionsResultItem();
            item.setId(c.getId());
            item.setAmount(c.getAmount());
            item.setTransferDate(c.getTransferDate());
            item.setDestinationAccountNumber(c.getDestinationAccountNumber());

            return item;
        }).collect(Collectors.toList());

        result.setItems(items);
        return result;
    }


}
