package com.example.account.service;

import com.example.account.GenericMappersMethods;
import com.example.account.api.transaction.GetTransactionsByTransferDateRequest;
import com.example.account.api.transaction.GetTransactionsByTransferDateResult;
import com.example.account.api.transaction.*;
import com.example.account.builder.TransactionBuilder;
import com.example.account.domain.AccountInfo;
import com.example.account.domain.Person;
import com.example.account.domain.Transaction;
import com.example.account.domain.TransactionInfo;
import com.example.account.repository.AccountInfoRepository;
import com.example.account.repository.PersonRepository;
import com.example.account.repository.TransactionRepository;
import commons.UtilAccount;
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

    @Value("${open.account.sign}")
    private String openAccountSign;

    public TransactionService(TransactionRepository transactionRepository,
                              PersonRepository personRepository,
                              AccountInfoRepository accountInfoRepository) {

        this.transactionRepository = transactionRepository;
        this.personRepository = personRepository;
        this.accountInfoRepository = accountInfoRepository;
    }

    public GetAllTransactionsResult getAllTransactions(GetAllTransactionsRequest request) {
        GetAllTransactionsResult result = new GetAllTransactionsResult();
        List<TransactionResult> transactions = transactionRepository.findAll().stream().map(this::transactionsMapper).collect(Collectors.toList());
        if (transactions.isEmpty() && transactions.size() == 0) {
            //transaction not found
        }
        result.setItems(transactions);

        return result;
    }

    public GetTransactionsBySourceAccountNumberResult getTransactionsBySourceAccountNumber(GetTransactionsBySourceAccountNumberRequest request) {
        GetTransactionsBySourceAccountNumberResult result = new GetTransactionsBySourceAccountNumberResult();

        List<TransactionResult> transactions = transactionRepository.findTransactionsBySourceAccountNumber(request.getAccountNumber())
                .stream().map(this::transactionsMapper).collect(Collectors.toList());

        if (transactions.isEmpty() && transactions.size() == 0) {
            //transactions not found
        }
        result.setItems(transactions);

        return result;
    }

    public GetTransactionsByDestAccountNumberResult getTransactionsByDestAccountNumber(GetTransactionsByDestAccountNumberRequest request) {
        GetTransactionsByDestAccountNumberResult result = new GetTransactionsByDestAccountNumberResult();

        List<TransactionResult> transactions = transactionRepository.findTransactionsByDestinationAccountNumber(request.getAccountNumber())
                .stream().map(this::transactionsMapper).collect(Collectors.toList());

        if (transactions.isEmpty() && transactions.size() == 0) {
            //transactions not found
        }
        result.setItems(transactions);

        return result;
    }

    public GetTransactionsByTransferDateResult getTransactionsByTransferDate(GetTransactionsByTransferDateRequest request) {
        GetTransactionsByTransferDateResult result = new GetTransactionsByTransferDateResult();
        List<TransactionResult> transactions = transactionRepository.findTransactionsByTransferDate(request.getTransferDate())
                .stream().map(this::transactionsMapper).collect(Collectors.toList());

        if (transactions.isEmpty() && transactions.size() == 0) {
            //transactions not found
        }
        result.setItems(transactions);

        return result;
    }

    private TransactionResult transactionsMapper(Transaction transaction) {
        TransactionResult result = new TransactionResult();
        GenericMappersMethods.transactionMapper(transaction, result);

        return result;
    }

    public InternalTransferResult internalTransfer(InternalTransferRequest request) {
        InternalTransferResult result = new InternalTransferResult();

        AccountInfo sourceAccountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getSourceAccountNumber());
        if (sourceAccountInfo == null) {
            //shomare hesabe mabda vojood nadarasd
        }

        Person sourcePerson = personRepository.findPersonByNationalCode(request.getNationalCode()).orElseThrow();
        if (sourcePerson == null) {
            //shakhs ba in shomare melli yaft nashod
        }

        if (!sourcePerson.getAccountInfo().equals(sourceAccountInfo)) {
            // in hesab moteallegh be in shakhs nemibashad
        }
        if (request.getAmount().compareTo(sourceAccountInfo.getBalance()) == 1) {
            // mojoodi nakafi ast
        }

        AccountInfo destAccountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getDestinationAccountNumber());
        if (destAccountInfo == null) {
            //shomare hesabe maghsad vojood nadarasd
        }

        Person destinationPerson = personRepository.findPersonByAccountInfo(destAccountInfo.getAccountNumber());
        if (destinationPerson == null) {
            //shakhs ba shomare hesab ... yaft nashod
        } else {
            // in shomare hesab moteallegh be shakhs dest mibashad
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
            //hesabe vojood nadarad
        }

        Person destPerson = personRepository.findPersonByAccountInfo(destAccountInfo.getAccountNumber());
        if (destPerson != null) {
            //in shomare hesab motealegh be ... ast
        } else {
            //shakhs ba in shomare hesab yaft nashod
        }
        if (request.getAmount() == null) {
            //mablagh nemitavanad sefr bashad
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
            //shomare hesabe mabda vojood nadarasd
        }

        Person sourcePerson = personRepository.findPersonByNationalCode(request.getNationalCode()).orElseThrow();
        if (sourcePerson == null) {
            //shakhs ba in shomare melli yaft nashod
        }

        if (!sourcePerson.getAccountInfo().equals(sourceAccountInfo)) {
            // in hesab moteallegh be in shakhs nemibashad
        }
        if (request.getAmount().compareTo(sourceAccountInfo.getBalance()) == 1) {
            // mojoodi nakafi ast
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

        List<GetOpenAccountTransactionsResultItem> items = customTransactions.stream().map(c-> {
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
