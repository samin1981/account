package com.example.account.api.base;

import com.example.account.api.person.AddPersonResult;
import com.example.account.api.person.RemovePersonRequest;
import com.example.account.api.person.RemovePersonResult;
import com.example.account.api.account.GetAllAccountInfosRequest;
import com.example.account.api.account.GetAllAccountInfosResult;
import com.example.account.api.account.OpenAnAccountRequest;
import com.example.account.api.account.OpenAnAccountResult;
import com.example.account.api.person.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

public interface AccAPI {

    @PostMapping(value = "/person/getAllPersons", produces = MediaType.APPLICATION_JSON)
    GetAllPersonsResult getAllPersons(@Valid @RequestBody GetAllPersonsRequest request);

    @PostMapping(value = "/person/getPerson", produces = MediaType.APPLICATION_JSON)
    GetPersonResult getPerson(@Valid @RequestBody GetPersonRequest request);

    @PostMapping(value = "/person/addPerson", produces = MediaType.APPLICATION_JSON)
    AddPersonResult addPerson(@Valid @RequestBody AddPersonRequest request);

    @PostMapping(value = "/person/removePerson", produces = MediaType.APPLICATION_JSON)
    RemovePersonResult removePerson(@Valid @RequestBody RemovePersonRequest request);

    @PostMapping(value = "/account/openAnAccount", produces = MediaType.APPLICATION_JSON)
    OpenAnAccountResult openAnAccount(@Valid @RequestBody OpenAnAccountRequest request);

    @PostMapping(value = "/account/getAllAccountInfos", produces = MediaType.APPLICATION_JSON)
    GetAllAccountInfosResult getAllAccountInfos(@Valid @RequestBody GetAllAccountInfosRequest request);

}
