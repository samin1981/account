package com.example.account.api.transaction;

import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
public class TransactionResult {
    private Integer id;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private BigDecimal srcBalance;
    private BigDecimal destBalance;
    private Date transferDate;
    private String trackingCode;
    private TransactionType transactionType;
    private TransferType srcTransferType;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id=        " + id)
                .add("sourceAccountNumber=        '" + UtilAccount.maskAccountNumber(sourceAccountNumber) + "'")
                .add("destinationAccountNumber=        '" + UtilAccount.maskAccountNumber(destinationAccountNumber) + "'")
                .add("amount=        " + UtilAccount.maskNumber(amount))
                .add("srcBalance=        " + UtilAccount.maskNumber(srcBalance))
                .add("destBalance=        " + UtilAccount.maskNumber(destBalance))
                .add("transferDate=        " + transferDate)
                .add("trackingCode=        '" + trackingCode + "'")
                .add("transactionType=        " + transactionType)
                .add("srcTransferType=        " + srcTransferType)
                .toString();
    }
}
