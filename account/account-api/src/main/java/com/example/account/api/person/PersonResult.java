package com.example.account.api.person;

import com.example.account.api.account.AccountInfoResult;
import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
public abstract class PersonResult {
    private Integer id;
    private String personName;
    private String nationalCode;
    private String phoneNumber;
    private AccountInfoResult accountInfo;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id=        " + id)
                .add("personName=        '" + personName + "'")
                .add("nationalCode=        '" + UtilAccount.maskNationalCode(nationalCode) + "'")
                .add("phoneNumber=        '" + phoneNumber + "'")
                .add("accountInfo=        " + accountInfo)
                .toString();
    }
}
