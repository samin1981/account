package com.example.account.service;

import com.example.account.api.account.CashWithdrawRequest;
import com.example.account.api.account.*;
import com.example.account.builder.AccountInfoBuilder;
import com.example.account.domain.AccountInfo;
import com.example.account.domain.Person;
import com.example.account.domain.Transaction;
import com.example.account.repository.AccountInfoRepository;
import com.example.account.repository.PersonRepository;
import com.example.account.repository.TransactionRepository;
import common.AccountError;
import common.AccountErrorsStatic;
import common.UtilAccount;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AccountInfoService {
    private static final Logger logger = LogManager.getLogger(AccountInfoService.class);

    private final AccountInfoRepository accountInfoRepository;
    private final PersonRepository personRepository;
    private final TransactionRepository transactionRepository;

    public AccountInfoService(AccountInfoRepository accountInfoRepository,
                              PersonRepository personRepository,
                              TransactionRepository transactionRepository) {
        this.accountInfoRepository = accountInfoRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
    }

    public GetAllAccountInfosResult getAllAccountInfos() {

        GetAllAccountInfosResult result = new GetAllAccountInfosResult();
        List<GetAccountInfoDetailResult> accountInfos = accountInfoRepository.findAll().stream().map(this::accountInfoDetailMapper).collect(Collectors.toList());
        if (accountInfos.isEmpty() && accountInfos.size() == 0) {
            try {
                logger.log(Level.INFO, "accounts info not found.");
                throw new AccountError(AccountErrorsStatic.ERROR_ACCOUNT_ACCOUNT_INFO_NOT_FOUND);
            } catch (AccountError accountError) {
                accountError.printStackTrace();
            }
        }
        result.setItems(accountInfos);

        return result;
    }

    public GetAccountInfoByAccountNumberResult getAccountInfoByAccountNumber(GetAccountInfoByAccountNumberRequest request) {
        AccountInfo accountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getAccountNumber);
        if (accountInfo == null) {
            //shomare hesab yaft nashod
        }
        GetAccountInfoByAccountNumberResult result = accountInfoMapper(accountInfo);

        return result;
    }

    public void openAnAccount(OpenAnAccountRequest request) {
        Person existPerson = personRepository.findPersonByNationalCode(request.getNationalCode()).orElseThrow();
        if (existPerson == null) {
            //shakhs ba in code melli yaft nashod
        }
        String accountNumber = UtilAccount.generateAccountNumber();
        AccountInfo accountInfo = AccountInfoBuilder.getInstance()
                .accountNumber(accountNumber)
                .amount(request.getAmount())
                .balance(request.getAmount())
                .transferDate(new Date())
                .transferType(TransferType.valueOf(TransferType.DEPOSIT.name()).code)
                .transactions(null)
                .build();
        accountInfoRepository.save(accountInfo);

        Transaction transaction = new Transaction();
        transaction.setAccountInfo(accountInfo);
        transactionRepository.save(transaction);

        existPerson.setAccountInfo(accountInfo);
        personRepository.save(existPerson);
    }

    public InternalTransferResult internalTransfer(InternalTransferRequest request) {
        InternalTransferResult result = new InternalTransferResult();

        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();

        transaction.setAmount(request.getAmount());
        transaction.setSourceAccountNumber(request.getSourceAccountNumber());
        transaction.setDestinationAccountNumber(request.getDestinationAccountNumber());
        transaction.setTransferDate(new Date());
        transaction.setTrackingCode(UtilAccount.generateTrackingCode());
        transaction.setTransactionTypeCode(TransactionType.INTERNAL.code);

        Person sourcePerson = personRepository.findPersonByNationalCode(request.getNationalCode()).orElseThrow();
        AccountInfo sourceAccountInfo = sourcePerson.getAccountInfo();

        if (!sourceAccountInfo.equals(request.getSourceAccountNumber())) {
            // shomare hesabe mabda marboot be in shakhs nemibashad
        }

        if (request.getAmount().compareTo(sourceAccountInfo.getBalance()) == 1) {
            // mojoodi nakafi ast
        }

        sourceAccountInfo.setAmount(request.getAmount());
        sourceAccountInfo.setBalance(sourceAccountInfo.getBalance().add(request.getAmount().negate()));
        sourceAccountInfo.setTransferTypeCode(TransferType.WITHDRAW.code);
        sourceAccountInfo.setTransferDate(transaction.getTransferDate());

        AccountInfo destinationAccountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getDestinationAccountNumber());

        if (destinationAccountInfo == null) {
            //hesabe maghsad vojood nadarad
        }

        Person destinationPerson = personRepository.findPersonByAccountInfo(request.getDestinationAccountNumber());

        destinationAccountInfo.setAmount(request.getAmount());
        destinationAccountInfo.setBalance(destinationAccountInfo.getBalance().add(request.getAmount()));
        destinationAccountInfo.setTransferDate(transaction.getTransferDate());
        destinationAccountInfo.setTransferTypeCode(TransferType.DEPOSIT.code);

        transactionRepository.save(transaction);
        transactions.add(transaction);

        sourceAccountInfo.setTransactions(transactions);
        destinationAccountInfo.setTransactions(transactions);
        accountInfoRepository.save(sourceAccountInfo);
        personRepository.save(sourcePerson);
        accountInfoRepository.save(destinationAccountInfo);
        personRepository.save(destinationPerson);

        result.setTrackingCode(transaction.getTrackingCode());
        return result;
    }

    public CashDepositResult cashDeposit(CashDepositRequest request) {
        CashDepositResult result = new CashDepositResult();

        Person person = personRepository.findPersonByAccountInfo(request.getAccountNumber());
        if (person == null) {
            // shakhs ba in shomare hesab yaft nashod
        }

        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setTransferDate(new Date());
        transaction.setDestinationAccountNumber(request.getAccountNumber());
        transaction.setSourceAccountNumber(null);
        transaction.setAmount(request.getAmount());
        transaction.setTrackingCode(UtilAccount.generateTrackingCode());
        transaction.setTransactionTypeCode(TransactionType.CASH.code);

        transactionRepository.save(transaction);
        transactions.add(transaction);

        AccountInfo accountInfo = person.getAccountInfo();

        accountInfo.setTransferDate(transaction.getTransferDate());
        accountInfo.setAmount(request.getAmount());
        accountInfo.setTransferTypeCode(TransferType.DEPOSIT.code);
        accountInfo.setBalance(accountInfo.getBalance().add(request.getAmount()));
        accountInfo.setAccountNumber(request.getAccountNumber());

        accountInfo.setTransactions(transactions);
        accountInfoRepository.save(accountInfo);

        personRepository.save(person);
        result.setTrackingCode(transaction.getTrackingCode());

        return result;
    }

    public CashWithdrawResult cashWithdraw(CashWithdrawRequest request) {
        CashWithdrawResult result = new CashWithdrawResult();

        Person person = personRepository.findPersonByAccountInfo(request.getAccountNumber());
        if (person == null) {
            // shakhs ba in shomare hesab yaft nashod
        }

        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setTransferDate(new Date());
        transaction.setDestinationAccountNumber(null);
        transaction.setSourceAccountNumber(request.getAccountNumber());
        transaction.setAmount(request.getAmount());
        transaction.setTrackingCode(UtilAccount.generateTrackingCode());
        transaction.setTransactionTypeCode(TransactionType.CASH.code);

        transactionRepository.save(transaction);
        transactions.add(transaction);

        AccountInfo accountInfo = person.getAccountInfo();

        accountInfo.setTransferDate(transaction.getTransferDate());
        accountInfo.setAmount(request.getAmount());
        accountInfo.setTransferTypeCode(TransferType.WITHDRAW.code);
        accountInfo.setBalance(accountInfo.getBalance().add(request.getAmount().negate()));
        accountInfo.setAccountNumber(request.getAccountNumber());

        accountInfo.setTransactions(transactions);
        accountInfoRepository.save(accountInfo);

        personRepository.save(person);
        result.setTrackingCode(transaction.getTrackingCode());

        return result;
    }


    private GetAccountInfoDetailResult accountInfoDetailMapper(AccountInfo source) {
        GetAccountInfoDetailResult destination = new GetAccountInfoDetailResult();

        destination.setId(source.getId());
        destination.setAccountNumber(source.getAccountNumber());
        destination.setAmount(source.getAmount());
        destination.setBalance(source.getBalance());
        destination.setTransferDate(source.getTransferDate());
        destination.setTransferType(com.example.account.api.TransferType.valueOfCode(source.getTransferTypeCode()));
        destination.setTransactions(null);

        return destination;
    }

    private GetAccountInfoByAccountNumberResult accountInfoMapper(AccountInfo source) {
        GetAccountInfoByAccountNumberResult destination = new GetAccountInfoByAccountNumberResult();

        destination.setId(source.getId());
        destination.setAccountNumber(source.getAccountNumber());
        destination.setAmount(source.getAmount());
        destination.setBalance(source.getBalance());
        destination.setTransferType(com.example.account.api.TransferType.valueOfCode(source.getTransferTypeCode()));
        destination.setTransferDate(source.getTransferDate());

        return destination;
    }


}
