package com.example.account.api.transaction;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
}
