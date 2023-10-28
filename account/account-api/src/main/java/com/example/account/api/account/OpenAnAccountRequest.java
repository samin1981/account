package com.example.account.api.account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class OpenAnAccountRequest {
    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String nationalCode;
    @NotNull
    private BigDecimal amount;

    private Boolean withdrawable;

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getWithdrawable() {
        return withdrawable;
    }

    public void setWithdrawable(Boolean withdrawable) {
        this.withdrawable = withdrawable;
    }
}
