package com.example.account.api.base;

import com.example.account.comon.UtilAccount;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

public class AccountNumberRequest {
    @NotNull
    @Pattern(regexp = "\\d{13}")
    public String accountNumber;

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
