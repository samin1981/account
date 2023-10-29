package com.example.account.api.transaction;

import com.example.account.api.base.TrackingCodeResult;

import java.util.StringJoiner;

public class CashDepositResult extends TrackingCodeResult {
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
