package com.example.account.api.person;

public class GetPersonByNationalCodeRequest {
    private String nationalCode;

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "GetPersonRequest{" +
                "nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
