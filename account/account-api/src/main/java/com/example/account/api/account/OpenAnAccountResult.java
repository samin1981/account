package com.example.account.api.account;

import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
public class OpenAnAccountResult {
    private String accountNumber;
        @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("accountNumber=        '" + UtilAccount.maskAccountNumber(accountNumber) + "'")
                .toString();
    }
}
