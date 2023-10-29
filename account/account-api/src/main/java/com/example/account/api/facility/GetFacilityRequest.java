package com.example.account.api.facility;

import com.example.account.api.account.OpenAnAccountRequest;
import com.example.account.comon.UtilAccount;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

public class GetFacilityRequest extends OpenAnAccountRequest {
    @NotNull
    @Pattern(regexp = "\\d{13}")
    private String facilityAccountNumber;
    public String getFacilityAccountNumber() {
        return facilityAccountNumber;
    }

    public void setFacilityAccountNumber(String facilityAccountNumber) {
        this.facilityAccountNumber = facilityAccountNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("facilityAccountNumber=        '" + UtilAccount.maskAccountNumber(facilityAccountNumber) + "'")
                .toString();
    }
}
