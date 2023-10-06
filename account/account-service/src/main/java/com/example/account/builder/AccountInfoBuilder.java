package com.example.account.builder;

import com.example.account.domain.AccountInfo;

public class AccountInfoBuilder {
    private String accountNumber;

    private AccountInfoBuilder() {
    }

    public static AccountInfoBuilder getInstance() {
        return new AccountInfoBuilder();
    }

    public AccountInfoBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountInfo build() {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccountNumber(accountNumber);

        return accountInfo;
    }
}
