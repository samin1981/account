package com.example.account.api.transaction;

import com.example.account.comon.UtilAccount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

public class GetOpenAccountTransactionsResultItem {
    private Integer id;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private Date transferDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id=        " + id)
                .add("destinationAccountNumber=        '" + UtilAccount.maskAccountNumber(destinationAccountNumber) + "'")
                .add("amount=        " + UtilAccount.maskNumber(amount))
                .add("transferDate=        " + transferDate)
                .toString();
    }
}
