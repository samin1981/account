package com.example.account.api.person;

import com.example.account.comon.UtilAccount;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

public class AddPersonRequest {
    private String personName;
    @NotNull
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
        return new StringJoiner(", ", "[", "]")
                .add("personName=        '" + personName + "'")
                .add("nationalCode=        '" + UtilAccount.maskNationalCode(nationalCode) + "'")
                .add("phoneNumber=        '" + phoneNumber + "'")
                .toString();
    }
}
