package com.example.account.api.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfulResult {
    private boolean successful;
    public SuccessfulResult(boolean successful) {
        this.successful = successful;
    }

    @Override
    public String toString() {
        return "AddPersonResult{" +
                "successful=" + successful +
                '}';
    }
}
