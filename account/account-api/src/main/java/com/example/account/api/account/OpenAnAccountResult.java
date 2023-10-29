package com.example.account.api.account;

import com.example.account.comon.UtilAccount;

import java.util.StringJoiner;

public class OpenAnAccountResult {
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("accountNumber=        '" + UtilAccount.maskAccountNumber(accountNumber) + "'")
                .toString();
    }
}
