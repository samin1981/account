package com.example.account.service;

import com.example.account.api.account.GetAccountInfoDetailResult;
import com.example.account.api.account.GetAllAccountInfosResult;
import com.example.account.api.account.OpenAnAccountRequest;
import com.example.account.builder.AccountInfoBuilder;
import com.example.account.domain.AccountInfo;
import com.example.account.domain.Person;
import com.example.account.repository.AccountInfoRepository;
import com.example.account.repository.PersonRepository;
import common.AccountError;
import common.AccountErrorsStatic;
import common.GenerateAccountNumber;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountInfoService {
    private static final Logger logger = LogManager.getLogger(AccountInfoService.class);

    private final AccountInfoRepository accountInfoRepository;
    private final PersonRepository personRepository;


    public AccountInfoService(AccountInfoRepository accountInfoRepository,
                              PersonRepository personRepository) {
        this.accountInfoRepository = accountInfoRepository;
        this.personRepository = personRepository;
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

    private GetAccountInfoDetailResult accountInfoDetailMapper(AccountInfo source) {
        GetAccountInfoDetailResult destination = new GetAccountInfoDetailResult();

        destination.setId(source.getId());
        destination.setAccountNumber(source.getAccountNumber());

        return destination;
    }

    public void openAnAccount(OpenAnAccountRequest request) {
        Person existPerson = personRepository.findByNationalCode(request.getNationalCode()).orElseThrow();
        String accountNumber = GenerateAccountNumber.generateAccountNumber();

        AccountInfo accountInfo = AccountInfoBuilder.getInstance()
                .accountNumber(accountNumber)
                .build();
        accountInfoRepository.save(accountInfo);
        if (existPerson.getAccountInfos().isEmpty() && existPerson.getAccountInfos().size() == 0) {
            List<AccountInfo> accountInfos = new ArrayList<>();
            accountInfos.add(accountInfo);
        }
        existPerson.getAccountInfos().add(accountInfo);

        personRepository.save(existPerson);
    }


}
