package com.example.account.api.facility;

import com.example.account.api.account.OpenAnAccountRequest;

public class GetFacilityRequest extends OpenAnAccountRequest {
    private String facilityAccountNumber;
    public String getFacilityAccountNumber() {
        return facilityAccountNumber;
    }

    public void setFacilityAccountNumber(String facilityAccountNumber) {
        this.facilityAccountNumber = facilityAccountNumber;
    }
}
