package com.example.account.api.impl;

import com.example.account.api.person.AddPersonResult;
import com.example.account.api.account.GetAllAccountInfosRequest;
import com.example.account.api.account.GetAllAccountInfosResult;
import com.example.account.api.account.OpenAnAccountRequest;
import com.example.account.api.account.OpenAnAccountResult;
import com.example.account.api.base.AccAPI;
import com.example.account.api.person.RemovePersonRequest;
import com.example.account.api.person.RemovePersonResult;
import com.example.account.api.person.*;
import com.example.account.service.AccountInfoService;
import com.example.account.service.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/accApi")
public class AccWS implements AccAPI {

    private final PersonService personService;
    private final AccountInfoService accountInfoService;

    public AccWS(PersonService personService,
                 AccountInfoService accountInfoService) {
        this.personService = personService;
        this.accountInfoService = accountInfoService;
    }

    @Override
    public GetAllPersonsResult getAllPersons(GetAllPersonsRequest request) {
        return personService.getAllPersons();
    }

    @Override
    public GetPersonResult getPerson(GetPersonRequest request) {
        return personService.getPerson(request);
    }

    @Override
    public AddPersonResult addPerson(AddPersonRequest request) {
        personService.addPerson(request);
        return new AddPersonResult(true);
    }

    @Override
    public RemovePersonResult removePerson(@Valid RemovePersonRequest request) {
        personService.removePerson(request);
        return new RemovePersonResult(true);
    }

    @Override
    public OpenAnAccountResult openAnAccount(@Valid OpenAnAccountRequest request) {
        accountInfoService.openAnAccount(request);
        return new OpenAnAccountResult(true);
    }

    @Override
    public GetAllAccountInfosResult getAllAccountInfos(@Valid GetAllAccountInfosRequest request) {
        return accountInfoService.getAllAccountInfos();
    }


}
