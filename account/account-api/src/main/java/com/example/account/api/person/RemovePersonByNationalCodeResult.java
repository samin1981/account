package com.example.account.api.person;

import com.example.account.api.base.SuccessfulResult;

import java.util.StringJoiner;

public class RemovePersonByNationalCodeResult extends SuccessfulResult {

    public RemovePersonByNationalCodeResult(boolean successful) {
        super(successful);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
