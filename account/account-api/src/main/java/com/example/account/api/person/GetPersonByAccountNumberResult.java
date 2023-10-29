package com.example.account.api.person;

import java.util.StringJoiner;

public class GetPersonByAccountNumberResult extends PersonResult {
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
