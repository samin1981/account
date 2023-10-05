package com.example.account.api;

public class RemovePersonRequest {

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
