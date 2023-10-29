package com.example.account.api.account;

import com.example.account.api.base.AccountNumberRequest;

import java.util.StringJoiner;

public class GetAccountInfoByAccountNumberRequest extends AccountNumberRequest {
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
