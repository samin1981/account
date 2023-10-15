package com.example.account.api.person;

import com.example.account.api.account.AccountInfoResult;

public abstract class PersonResult {
    private Integer id;
    private String personName;
    private String nationalCode;
    private String phoneNumber;
    private AccountInfoResult accountInfo;

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

    public AccountInfoResult getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfoResult accountInfo) {
        this.accountInfo = accountInfo;
    }
}
