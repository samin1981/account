package com.example.account.api.person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RemovePersonByNationalCodeRequest {
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
        return "RemovePersonRequest{" +
                "nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
