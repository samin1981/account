package com.example.account.service;

import com.example.account.GenericMappersMethods;
import com.example.account.api.account.*;
import com.example.account.api.transaction.*;
import com.example.account.builder.AccountInfoBuilder;
import com.example.account.builder.TransactionBuilder;
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

import java.math.BigDecimal;
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
        List<GetAccountInfoDetailResult> accountInfos = accountInfoRepository.findAll().stream().map(this::accountInfosMapper).collect(Collectors.toList());
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

    private GetAccountInfoDetailResult accountInfosMapper(AccountInfo accountInfo) {
        GetAccountInfoDetailResult result = new GetAccountInfoDetailResult();
        result = GenericMappersMethods.accountInfoMapper(accountInfo, result);

        return result;
    }

    public GetAccountInfoByAccountNumberResult getAccountInfoByAccountNumber(GetAccountInfoByAccountNumberRequest request) {
        AccountInfo accountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getAccountNumber());
        if (accountInfo == null) {
            //shomare hesab yaft nashod
        }

        GetAccountInfoByAccountNumberResult result = new GetAccountInfoByAccountNumberResult();
        result = GenericMappersMethods.accountInfoMapper(accountInfo, result);

        return result;
    }

    public void openAnAccount(OpenAnAccountRequest request) {
        BigDecimal newAmount = new BigDecimal(100);
        if (request.getAmount().compareTo(newAmount) < 0) {
            //baraye eftetahe hesab hadeaghal 100 rial niaz ast
        }
        Person existPerson = personRepository.findPersonByNationalCode(request.getNationalCode()).orElseThrow();
        if (existPerson == null) {
            //shakhs ba in code melli yaft nashod
        }
        if (existPerson.getAccountInfo() != null) {
            //ghablan sakhs eftetah hesab dashte
        }

        String destinationAccountNumber = UtilAccount.generateAccountNumber();
        AccountInfo accountInfo = AccountInfoBuilder.getInstance()
                .accountNumber(destinationAccountNumber)
                .amount(request.getAmount())
                .balance(request.getAmount())
                .transferTypeCode(TransferType.DEPOSIT.code)
                .build();
        accountInfoRepository.save(accountInfo);

        Transaction transaction = TransactionBuilder.getInstance()
                .sourceAccountNumber(null)
                .destinationAccountNumber(destinationAccountNumber)
                .transferDate(new Date())
                .transactionTypeCode(TransactionType.CASH.code)
                .srcTransferTypeCode(null)
                .amount(accountInfo.getAmount())
                .destBalance(accountInfo.getBalance())
                .trackingCode("OAAC_" + UtilAccount.generateTrackingCode())
                .build();

        transactionRepository.save(transaction);
    }


}
