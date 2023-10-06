package com.example.account.api.person;

import com.example.account.api.account.AccountInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class AddPersonRequest {

    private String personName;

    @NotNull(message = "nationalCode may not be null.")
    @Pattern(regexp = "\\d{10}")
    private String nationalCode;

    @Pattern(regexp = "\\d{11}")
    private String phoneNumber;

    @NotNull
    @Valid
    private List<AccountInfo> accountInfos;

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

    public List<AccountInfo> getAccountInfos() {
        return accountInfos;
    }

    public void setAccountInfos(List<AccountInfo> accountInfos) {
        this.accountInfos = accountInfos;
    }

    @Override
    public String toString() {
        return "AddPersonRequest{" +
                "personName='" + personName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accountInfos=" + accountInfos +
                '}';
    }
}
