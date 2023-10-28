package com.example.account.api.base;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
}
