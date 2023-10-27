package com.example.account.builder;

import com.example.account.domain.AccountInfo;
import com.example.account.domain.Person;

public class PersonBuilder {

    private String personName;
    private String nationalCode;
    private String phoneNumber;
    private Integer deleted;
    private AccountInfo accountInfo;

    private PersonBuilder() {
    }

    public static PersonBuilder getInstance() {
        return new PersonBuilder();
    }

    public PersonBuilder personName(String personName) {
        this.personName = personName;
        return this;
    }

    public PersonBuilder nationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    public PersonBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PersonBuilder deleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    public PersonBuilder accountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
        return this;
    }

    public Person build() {
        Person person = new Person();
        person.setPersonName(personName);
        person.setPhoneNumber(phoneNumber);
        person.setNationalCode(nationalCode);
        person.setDeleted(deleted);
        person.setAccountInfo(accountInfo);

        return person;
    }
}
