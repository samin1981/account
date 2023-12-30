package com.example.account.api.transaction;

import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.StringJoiner;

@Getter
@Setter
public class CashRequest {
    @NotNull
    private BigDecimal amount;
    @NotNull
    @Pattern(regexp = "\\d{13}")
    private String accountNumber;
    private TransferType transferType;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("amount=        " + amount)
                .add("accountNumber=        '" + UtilAccount.maskAccountNumber(accountNumber) + "'")
                .add("transferType=        " + transferType)
                .toString();
    }
}
