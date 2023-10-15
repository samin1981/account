package com.example.account.api.base;

public class SuccessfulResult {
    private boolean successful;

    public SuccessfulResult(boolean successful) {
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
