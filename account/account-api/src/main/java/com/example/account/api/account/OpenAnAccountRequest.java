package com.example.account.api.account;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class OpenAnAccountRequest {

    @Pattern(regexp = "\\d{10}")
    private String nationalCode;

    private BigDecimal amount;

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

    @Override
    public String toString() {
        return "OpenAnAccountRequest{" +
                "amount='" + amount + '\'' +
                "nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
