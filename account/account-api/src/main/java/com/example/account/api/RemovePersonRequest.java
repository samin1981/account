package com.example.account.api;

import javax.validation.constraints.Pattern;

public class RemovePersonRequest {

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
