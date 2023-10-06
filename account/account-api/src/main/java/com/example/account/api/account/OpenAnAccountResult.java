package com.example.account.api.account;

public class OpenAnAccountResult {
    private boolean successful;

    public OpenAnAccountResult(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
