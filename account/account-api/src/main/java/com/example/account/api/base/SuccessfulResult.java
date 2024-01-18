package com.example.account.api.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SuccessfulResult {
    private boolean successful;
    @Override
    public String toString() {
        return "AddPersonResult{" +
                "successful=" + successful +
                '}';
    }
}
