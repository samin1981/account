package com.example.account.service;

import com.example.account.api.account.*;
import com.example.account.builder.AccountInfoBuilder;
import com.example.account.builder.TransactionBuilder;
import com.example.account.comon.AccountErrorsStatic;
import com.example.account.comon.AccountException;
import com.example.account.comon.UtilAccount;
import com.example.account.domain.AccountInfo;
import com.example.account.domain.Person;
import com.example.account.domain.Transaction;
import com.example.account.helper.AccountMapper;
import com.example.account.repository.AccountInfoRepository;
import com.example.account.repository.PersonRepository;
import com.example.account.repository.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final AccountMapper accountMapper;

    @Value("${open.account.sign}")
    private String openAccountSign;

    @Value("${amount.for.open.account}")
    private BigDecimal amountForOpenAccount;

    public AccountInfoService(AccountInfoRepository accountInfoRepository,
                              PersonRepository personRepository,
                              TransactionRepository transactionRepository,
                              AccountMapper accountMapper) {
        this.accountInfoRepository = accountInfoRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
        this.accountMapper = accountMapper;
    }

    public GetAllAccountInfosResult getAllAccountInfos(GetAllAccountInfosRequest request) {

        GetAllAccountInfosResult result = new GetAllAccountInfosResult();
        Pageable paging = PageRequest.of(request.getStartIndex(), request.getPageSize());
        List<GetAccountInfoDetailResult> accountInfos = accountInfoRepository.findAllAccountInfos(paging).stream()
                .map(accountInfo -> accountMapper.accountInfosMapperForAccountInfoDetail(accountInfo)).collect(Collectors.toList());
        if (accountInfos.isEmpty() && accountInfos.size() == 0) {
            throw new AccountException(AccountErrorsStatic.ERROR_ACCOUNT_INFO_NOT_FOUND, null);
        }
        result.setItems(accountInfos);

        return result;
    }

    public GetAccountInfoByAccountNumberResult getAccountInfoByAccountNumber(GetAccountInfoByAccountNumberRequest request) {

        AccountInfo accountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getAccountNumber());
        if (accountInfo == null) {
            throw new AccountException(AccountErrorsStatic.ERROR_ACCOUNT_INFO_NOT_FOUND, request.getAccountNumber());
        }
        return accountInfosMapperForAccountInfoByAccountNumber(accountInfo);
    }

    private GetAccountInfoByAccountNumberResult accountInfosMapperForAccountInfoByAccountNumber(AccountInfo accountInfo) {
        GetAccountInfoByAccountNumberResult result = new GetAccountInfoByAccountNumberResult();
        result.setId(accountInfo.getId());
        result.setAccountNumber(accountInfo.getAccountNumber());
        result.setAmount(accountInfo.getAmount());
        result.setBalance(accountInfo.getBalance());
        result.setTransferType(com.example.account.api.transaction.TransferType.valueOfCode(accountInfo.getTransferTypeCode()));

        return result;
    }

    public OpenAnAccountResult openAnAccount(OpenAnAccountRequest request) {
        OpenAnAccountResult result = new OpenAnAccountResult();

        BigDecimal newAmount = new BigDecimal(amountForOpenAccount.longValue());
        if (request.getAmount().compareTo(newAmount) < 0) {
            throw new AccountException(AccountErrorsStatic.ERROR_MIN_AMOUNT_NECESSARY, amountForOpenAccount);
        }

        Person existPerson = personRepository.findPersonByNationalCode(request.getNationalCode())
                .orElseThrow(() -> new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, request.getNationalCode()));

        if (existPerson.getAccountInfo() != null) {
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_WITH_ACCOUNT_NUMBER_NOT_FOUND, request.getNationalCode());
        }

        String destinationAccountNumber = UtilAccount.generateAccountNumber();
        AccountInfo accountInfo = AccountInfoBuilder.getInstance()
                .accountNumber(destinationAccountNumber)
                .amount(request.getAmount())
                .balance(request.getAmount())
                .transferTypeCode(TransferType.DEPOSIT.code)
                .withdrawable(request.getWithdrawable() ? 1 : 0)
                .build();

        accountInfoRepository.save(accountInfo);

        existPerson.setAccountInfo(accountInfo);
        personRepository.save(existPerson);

        Transaction transaction = TransactionBuilder.getInstance()
                .sourceAccountNumber(null)
                .destinationAccountNumber(destinationAccountNumber)
                .transferDate(new Date())
                .transactionTypeCode(TransactionType.CASH.code)
                .srcTransferTypeCode(null)
                .amount(accountInfo.getAmount())
                .destBalance(accountInfo.getBalance())
                .trackingCode(openAccountSign + UtilAccount.generateTrackingCode())
                .build();

        transactionRepository.save(transaction);
        result.setAccountNumber(accountInfo.getAccountNumber());

        return result;
    }

}
