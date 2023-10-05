package com.example.account.builder;

import com.example.account.domain.Person;

public class PersonBuilder {

    private String personName;
    private String nationalCode;
    private String phoneNumber;

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

    public Person build() {
        Person person = new Person();
        person.setPersonName(personName);
        person.setPhoneNumber(phoneNumber);
        person.setNationalCode(nationalCode);

        return person;
    }
}
