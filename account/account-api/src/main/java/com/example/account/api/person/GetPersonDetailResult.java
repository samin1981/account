package com.example.account.api.person;

import com.example.account.api.account.AccountInfos;

import java.util.List;

public class GetPersonDetailResult {

    private Integer id;
    private String personName;
    private String nationalCode;
    private String phoneNumber;
    private List<AccountInfos> accountInfos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<AccountInfos> getAccountInfos() {
        return accountInfos;
    }

    public void setAccountInfos(List<AccountInfos> accountInfos) {
        this.accountInfos = accountInfos;
    }
}
