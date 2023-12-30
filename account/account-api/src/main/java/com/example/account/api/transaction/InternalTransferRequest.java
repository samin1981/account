package com.example.account.api.transaction;

import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
public class InternalTransferRequest {
    @NotNull
    private BigDecimal amount;
    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String nationalCode;
    @NotNull
    @Pattern(regexp = "\\d{13}")
    private String sourceAccountNumber;
    @NotNull
    @Pattern(regexp = "\\d{13}")
    private String destinationAccountNumber;

    @Override
    public String toString() {
        return "InternalTransferRequest{" +
                "amount=" + amount +
                ", nationalCode='" + UtilAccount.maskNationalCode(nationalCode) + '\'' +
                ", fromAccountNumber='" + UtilAccount.maskAccountNumber(sourceAccountNumber) + '\'' +
                ", toAccountNumber='" + UtilAccount.maskAccountNumber(destinationAccountNumber) + '\'' +
                '}';
    }
}
