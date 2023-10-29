package com.example.account.api.transaction;

import com.example.account.api.base.AccountNumberRequest;

import java.util.StringJoiner;

public class GetTransactionsBySourceAccountNumberRequest extends AccountNumberRequest {
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
