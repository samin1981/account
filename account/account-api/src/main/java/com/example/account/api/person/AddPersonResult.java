package com.example.account.api.person;

import com.example.account.api.base.SuccessfulResult;

import java.util.StringJoiner;

public class AddPersonResult extends SuccessfulResult {
    public AddPersonResult(boolean successful) {
        super(successful);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
