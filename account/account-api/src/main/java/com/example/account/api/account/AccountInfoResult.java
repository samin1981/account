package com.example.account.api.account;

import com.example.account.api.transaction.TransferType;

import java.math.BigDecimal;

public class AccountInfoResult {
    private Integer id;
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal amount;
    private TransferType transferType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }
    @Override
    public String toString() {
        return "AccountInfo{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", amount=" + amount +
                ", transferType=" + transferType +
                '}';
    }
}
