package com.example.account.builder;

import com.example.account.domain.AccountInfo;

import java.math.BigDecimal;

public class AccountInfoBuilder {
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal amount;
    private Integer transferTypeCode;
    private AccountInfoBuilder() {
    }

    public static AccountInfoBuilder getInstance() {
        return new AccountInfoBuilder();
    }

    public AccountInfoBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountInfoBuilder balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public AccountInfoBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public AccountInfoBuilder transferTypeCode(Integer transferTypeCode) {
        this.transferTypeCode = transferTypeCode;
        return this;
    }
    public AccountInfo build() {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccountNumber(accountNumber);
        accountInfo.setBalance(balance);
        accountInfo.setAmount(amount);
        accountInfo.setTransferTypeCode(transferTypeCode);

        return accountInfo;
    }
}
