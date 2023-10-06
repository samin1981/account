package com.example.account.api.person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddPersonRequest {

    private String personName;

    @NotNull(message = "nationalCode may not be null.")
    @Pattern(regexp = "\\d{10}")
    private String nationalCode;

    @Pattern(regexp = "\\d{11}")
    private String phoneNumber;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "AddPersonRequest{" +
                "personName='" + personName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
