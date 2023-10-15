package com.example.account.builder;

import com.example.account.domain.Transaction;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionBuilder {
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private BigDecimal srcBalance;

    private BigDecimal destBalance;
    private Date transferDate;
    private String trackingCode;
    private Integer transactionTypeCode;
    private Integer srcTransferTypeCode;
    private TransactionBuilder() {
    }

    public static TransactionBuilder getInstance() {
        return new TransactionBuilder();
    }

    public TransactionBuilder sourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
        return this;
    }

    public TransactionBuilder destinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
        return this;
    }

    public TransactionBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder srcBalance(BigDecimal srcBalance) {
        this.srcBalance = srcBalance;
        return this;
    }

    public TransactionBuilder destBalance(BigDecimal destBalance) {
        this.destBalance = destBalance;
        return this;
    }

    public TransactionBuilder transferDate(Date transferDate) {
        this.transferDate = transferDate;
        return this;
    }

    public TransactionBuilder trackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
        return this;
    }

    public TransactionBuilder transactionTypeCode(Integer transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
        return this;
    }

    public TransactionBuilder srcTransferTypeCode(Integer srcTransferTypeCode) {
        this.srcTransferTypeCode = srcTransferTypeCode;
        return this;
    }
    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setSourceAccountNumber(sourceAccountNumber);
        transaction.setDestinationAccountNumber(destinationAccountNumber);
        transaction.setSrcBalance(srcBalance);
        transaction.setDestBalance(destBalance);
        transaction.setAmount(amount);
        transaction.setTrackingCode(trackingCode);
        transaction.setTransactionTypeCode(transactionTypeCode);
        transaction.setSrcTransferTypeCode(srcTransferTypeCode);
        transaction.setTransferDate(transferDate);

        return transaction;
    }
}
