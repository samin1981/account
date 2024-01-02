package com.example.account.api.person;

import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

@Getter
@Setter
public class AddPersonRequest {
    private String personName;
    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String nationalCode;

    @Pattern(regexp = "\\d{11}")
    private String phoneNumber;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("personName=        '" + personName + "'")
                .add("nationalCode=        '" + UtilAccount.maskNationalCode(nationalCode) + "'")
                .add("phoneNumber=        '" + phoneNumber + "'")
                .toString();
    }
}
