package com.example.account.api.person;

import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

@Getter
@Setter
public class RemovePersonByNationalCodeRequest {
    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String nationalCode;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("nationalCode=        '" + UtilAccount.maskNationalCode(nationalCode) + "'")
                .toString();
    }
}
