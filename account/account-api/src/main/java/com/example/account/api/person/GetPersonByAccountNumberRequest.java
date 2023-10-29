package com.example.account.api.person;

import com.example.account.api.base.AccountNumberRequest;

import java.util.StringJoiner;

public class GetPersonByAccountNumberRequest extends AccountNumberRequest {
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
