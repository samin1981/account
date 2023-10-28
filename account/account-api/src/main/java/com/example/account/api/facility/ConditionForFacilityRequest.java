package com.example.account.api.facility;

import org.springframework.security.access.method.P;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ConditionForFacilityRequest {
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
