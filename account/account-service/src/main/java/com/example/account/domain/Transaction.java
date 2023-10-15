package com.example.account.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "TRANSACTION_ID_SEQ", allocationSize = 1)
    private Integer id;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private BigDecimal srcBalance;
    private BigDecimal destBalance;
    private Date transferDate;
    private String trackingCode;
    private Integer transactionTypeCode;
    private Integer srcTransferTypeCode;

    @Version
    private Timestamp version;

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

    public void setSrcBalance(BigDecimal balance) {
        this.srcBalance = balance;
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

    public Integer getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setTransactionTypeCode(Integer transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    public Integer getSrcTransferTypeCode() {
        return srcTransferTypeCode;
    }

    public void setSrcTransferTypeCode(Integer srcTransferTypeCode) {
        this.srcTransferTypeCode = srcTransferTypeCode;
    }
}
