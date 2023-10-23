package com.example.account.builder;

import com.example.account.domain.Facility;

import java.math.BigDecimal;
import java.util.Date;

public class FacilityBuilder {
    private BigDecimal creditAmount;
    private BigDecimal paymentAmount;
    private BigDecimal amountForReturn;
    private Date creditDate;
    private Date firstPaymentDate;
    private Date lastPaymentDate;
    private BigDecimal lateFineAmount;

    private FacilityBuilder() {
    }

    public static FacilityBuilder getInstance() {
        return new FacilityBuilder();
    }

    public FacilityBuilder creditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
        return this;
    }

    public FacilityBuilder paymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public FacilityBuilder amountForReturn(BigDecimal amountForReturn) {
        this.amountForReturn = amountForReturn;
        return this;
    }
    public FacilityBuilder creditDate(Date creditDate) {
        this.creditDate = creditDate;
        return this;
    }

    public FacilityBuilder firstPaymentDate(Date firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
        return this;
    }

    public FacilityBuilder lastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
        return this;
    }

    public FacilityBuilder lateFineAmount(BigDecimal lateFineAmount) {
        this.lateFineAmount = lateFineAmount;
        return this;
    }
    public Facility build() {
        Facility facility = new Facility();
        facility.setCreditAmount(creditAmount);
        facility.setAmountForReturn(amountForReturn);
        facility.setCreditDate(creditDate);
        facility.setFirstPaymentDate(firstPaymentDate);
        facility.setLastPaymentDate(lastPaymentDate);
        facility.setPaymentAmount(paymentAmount);
        facility.setLateFineAmount(lateFineAmount);

        return facility;
    }
}
