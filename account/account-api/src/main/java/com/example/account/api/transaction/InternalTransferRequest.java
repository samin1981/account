package com.example.account.api.transaction;

import java.math.BigDecimal;

public class InternalTransferRequest {
    private BigDecimal amount;
    private String nationalCode;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }
    @Override
    public String toString() {
        return "InternalTransferRequest{" +
                "amount=" + amount +
                ", nationalCode='" + nationalCode + '\'' +
                ", fromAccountNumber='" + sourceAccountNumber + '\'' +
                ", toAccountNumber='" + destinationAccountNumber + '\'' +
                '}';
    }
}
