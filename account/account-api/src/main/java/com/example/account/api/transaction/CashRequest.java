package com.example.account.api.transaction;

import com.example.account.api.transaction.TransferType;

import java.math.BigDecimal;

public class CashRequest {
    private BigDecimal amount;
    private String accountNumber;
    private TransferType transferType;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }
}
