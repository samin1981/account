package com.example.account.api.base;


import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

@Getter
@Setter
public class AccountNumberRequest {
    @NotNull
    @Pattern(regexp = "\\d{13}")
    public String accountNumber;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("accountNumber=        '" + UtilAccount.maskAccountNumber(accountNumber) + "'")
                .toString();
    }
}
