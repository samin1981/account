package com.example.account.api.person;

public class GetPersonRequest {

    private String personName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public String toString() {
        return "GetPersonRequest{" +
                "personName=" + personName +
                '}';
    }
}
