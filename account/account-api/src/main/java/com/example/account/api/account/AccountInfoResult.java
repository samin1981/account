package com.example.account.api.account;

import com.example.account.api.transaction.TransferType;
import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.StringJoiner;

@Getter
@Setter
public class AccountInfoResult {
    private Integer id;
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal amount;
    private TransferType transferType;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id=        " + id)
                .add("accountNumber=        '" + UtilAccount.maskAccountNumber(accountNumber) + "'")
                .add("balance=        " + UtilAccount.maskNumber(balance))
                .add("amount=        " + UtilAccount.maskNumber(amount))
                .add("transferType=        " + transferType)
                .toString();
    }
}
