package com.example.account.api.facility;

import com.example.account.comon.UtilAccount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

public class GetFacilityResultItem {
    private BigDecimal creditAmount;
    private BigDecimal paymentAmount;
    private Date creditDate;
    private Date firsPaymentDate;

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
    }

    public Date getFirsPaymentDate() {
        return firsPaymentDate;
    }

    public void setFirsPaymentDate(Date firsPaymentDate) {
        this.firsPaymentDate = firsPaymentDate;
    }

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
