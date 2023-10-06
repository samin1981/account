package com.example.account.api.account;

public class GetAccountInfoDetailResult {
    private Integer id;
    private String accountNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "GetAccountInfoDetailResult{" +
                "id='" + id + '\'' +
                "accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
