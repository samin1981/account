package com.example.account.api.facility;

import com.example.account.api.base.SuccessfulResult;

import java.util.StringJoiner;

public class ConditionForFacilityResult extends SuccessfulResult {
    public ConditionForFacilityResult(boolean successful) {
        super(successful);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
