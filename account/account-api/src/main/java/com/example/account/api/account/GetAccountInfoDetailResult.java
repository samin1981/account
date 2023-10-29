package com.example.account.api.account;

import java.util.StringJoiner;

public class GetAccountInfoDetailResult extends AccountInfoResult {
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
