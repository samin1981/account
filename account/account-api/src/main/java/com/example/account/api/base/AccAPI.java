package com.example.account.api.base;

import com.example.account.api.AddPersonResult;
import com.example.account.api.RemovePersonRequest;
import com.example.account.api.RemovePersonResult;
import com.example.account.api.person.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

public interface AccAPI {

    @PostMapping(value = "/getAllPersons", produces = MediaType.APPLICATION_JSON)
    GetAllPersonsResult getAllPersons(@Valid @RequestBody GetAllPersonsRequest request);

    @PostMapping(value = "/getPerson", produces = MediaType.APPLICATION_JSON)
    GetPersonResult getPerson(@Valid @RequestBody GetPersonRequest request);

    @PostMapping(value = "/addPerson", produces = MediaType.APPLICATION_JSON)
    AddPersonResult addPerson(@Valid @RequestBody AddPersonRequest request);

    @PostMapping(value = "/removePerson", produces = MediaType.APPLICATION_JSON)
    RemovePersonResult removePerson(@Valid @RequestBody RemovePersonRequest request);
}
