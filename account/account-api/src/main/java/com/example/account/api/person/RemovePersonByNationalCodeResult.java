package com.example.account.api.person;

public class RemovePersonByNationalCodeResult {
    private boolean successful;

    public RemovePersonByNationalCodeResult(boolean successful) {
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
