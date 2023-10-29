package com.example.account.api.transaction;

import com.example.account.api.transaction.TransferType;
import com.example.account.comon.UtilAccount;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.StringJoiner;

public class CashRequest {
    @NotNull
    private BigDecimal amount;
    @NotNull
    @Pattern(regexp = "\\d{13}")
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

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("amount=        " + amount)
                .add("accountNumber=        '" + UtilAccount.maskAccountNumber(accountNumber) + "'")
                .add("transferType=        " + transferType)
                .toString();
    }
}
