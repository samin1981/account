package com.example.account.api.facility;

import com.example.account.comon.UtilAccount;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
public class GetFacilityResultItem {
    private BigDecimal creditAmount;
    private BigDecimal paymentAmount;
    private Date creditDate;
    private Date firsPaymentDate;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("creditAmount=        " + UtilAccount.maskNumber(creditAmount))
                .add("paymentAmount=        " + paymentAmount)
                .add("creditDate=        " + creditDate)
                .add("firsPaymentDate=        " + firsPaymentDate)
                .toString();
    }
}
