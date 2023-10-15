package com.example.account.api.transaction;

import java.sql.Date;

public class GetTransactionsByTransferDateRequest {
    private Date transferDate;

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }
}
