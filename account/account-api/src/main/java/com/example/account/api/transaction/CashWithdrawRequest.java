package com.example.account.api.transaction;

import com.example.account.comon.UtilAccount;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

public class CashWithdrawRequest extends CashRequest {
    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String nationalCode;
    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("nationalCode=        '" + UtilAccount.maskNationalCode(nationalCode) + "'")
                .toString();
    }
}
