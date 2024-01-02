package com.example.account.api.facility;

import com.example.account.api.account.OpenAnAccountRequest;
import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

@Getter
@Setter
public class GetFacilityRequest extends OpenAnAccountRequest {
    @NotNull
    @Pattern(regexp = "\\d{13}")
    private String facilityAccountNumber;
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("facilityAccountNumber=        '" + UtilAccount.maskAccountNumber(facilityAccountNumber) + "'")
                .toString();
    }
}
