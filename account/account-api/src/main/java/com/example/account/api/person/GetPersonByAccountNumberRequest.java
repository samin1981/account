package com.example.account.api.person;

public class GetPersonByAccountNumberRequest {

    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "GetPersonByAccountNumberRequest{" +
                "accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
