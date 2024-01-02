package com.example.account.api.account;

import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.StringJoiner;
@Getter
@Setter
public class OpenAnAccountRequest {
    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String nationalCode;
    @NotNull
    private BigDecimal amount;

    private Boolean withdrawable;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("nationalCode=        '" + UtilAccount.maskNationalCode(nationalCode) + "'")
                .add("amount=        " + amount)
                .add("withdrawable=        " + withdrawable)
                .toString();
    }
}
