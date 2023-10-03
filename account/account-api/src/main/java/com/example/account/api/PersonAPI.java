package com.example.account.api;

import com.example.account.api.person.GetAllPersonsRequest;
import com.example.account.api.person.GetAllPersonsResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface PersonAPI {

    @RequestMapping(value = "/getAllPersons", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public GetAllPersonsResult getAllPersons();
//    public GetAllPersonsResult getAllPersons(@Valid @RequestBody GetAllPersonsRequest request);
}
