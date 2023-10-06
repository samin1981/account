package com.example.account.api.impl;

import com.example.account.api.AddPersonResult;
import com.example.account.api.base.AccAPI;
import com.example.account.api.RemovePersonRequest;
import com.example.account.api.RemovePersonResult;
import com.example.account.api.person.*;
import com.example.account.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/accApi")
public class AccWS implements AccAPI {

    @Autowired
    private final PersonService personService;

    public AccWS(PersonService personService) {
        this.personService = personService;
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

}
