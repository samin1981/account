package com.example.account.api.transaction;

import java.util.StringJoiner;

public class CashDepositRequest extends CashRequest {
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
