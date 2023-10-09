package com.example.account.builder;

import com.example.account.domain.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AccountInfoBuilder {
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal amount;
    private int transferType;
    private Date transferDate;
    private List<Transaction> transactions;

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

    public AccountInfoBuilder transferType(int transferType) {
        this.transferType = transferType;
        return this;
    }

    public AccountInfoBuilder transferDate(Date transferDate) {
        this.transferDate = transferDate;
        return this;
    }

    public AccountInfoBuilder transactions(List<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public AccountInfo build() {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccountNumber(accountNumber);
        accountInfo.setBalance(balance);
        accountInfo.setAmount(amount);
        accountInfo.setTransferTypeCode(transferType);
        accountInfo.setTransferDate(transferDate);
        accountInfo.setTransactions(transactions);

        return accountInfo;
    }
}
