package com.example.account.api.person;

public class AddPersonResult {

    private boolean successful;

    public AddPersonResult(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    @Override
    public String toString() {
        return "AddPersonResult{" +
                "successful=" + successful +
                '}';
    }
}
