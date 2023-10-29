package com.example.account.api.transaction;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.StringJoiner;

public class GetTransactionsByTransferDateRequest {
    @DateTimeFormat
    private Date transferDate;

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("transferDate=        " + transferDate)
                .toString();
    }
}
