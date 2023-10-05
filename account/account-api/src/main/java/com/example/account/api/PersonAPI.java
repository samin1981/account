package com.example.account.api;

import com.example.account.api.person.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

public interface PersonAPI {

    @PostMapping(value = "/getAllPersons", produces = MediaType.APPLICATION_JSON)
    GetAllPersonsResult getAllPersons(@RequestBody @Valid GetAllPersonsRequest request);

    @PostMapping(value = "/getPerson", produces = MediaType.APPLICATION_JSON)
    GetPersonResult getPerson(@RequestBody @Valid GetPersonRequest request);

    @PostMapping(value = "/addPerson", produces = MediaType.APPLICATION_JSON)
    AddPersonResult addPerson(@RequestBody @Valid AddPersonRequest request);

    @PostMapping(value = "/removePerson", produces = MediaType.APPLICATION_JSON)
    RemovePersonResult removePerson(@RequestBody @Valid RemovePersonRequest request);
}
