package com.example.account.api;

public class RemovePersonResult {
    private boolean successful;

    public RemovePersonResult(boolean successful) {
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
        return "RemovePersonResult{" +
                "successful=" + successful +
                '}';
    }
}
