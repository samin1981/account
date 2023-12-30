package com.example.account.api.transaction;

import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
public class GetOpenAccountTransactionsResultItem {
    private Integer id;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private Date transferDate;

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
