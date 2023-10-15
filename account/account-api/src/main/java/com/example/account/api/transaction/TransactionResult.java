package com.example.account.api.transaction;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionResult {
    private Integer id;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private BigDecimal srcBalance;
    private BigDecimal destBalance;
    private Date transferDate;
    private String trackingCode;
    private TransactionType transactionTypeCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSrcBalance() {
        return srcBalance;
    }

    public void setSrcBalance(BigDecimal srcBalance) {
        this.srcBalance = srcBalance;
    }

    public BigDecimal getDestBalance() {
        return destBalance;
    }

    public void setDestBalance(BigDecimal destBalance) {
        this.destBalance = destBalance;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public TransactionType getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setTransactionTypeCode(TransactionType transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }
}
