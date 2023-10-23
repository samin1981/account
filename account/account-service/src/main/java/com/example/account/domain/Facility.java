package com.example.account.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "facility")
public class Facility {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facility_id_seq")
    @SequenceGenerator(name = "facility_id_seq", sequenceName = "FACILITY_ID_SEQ", allocationSize = 1)
    private Integer id;
    private BigDecimal creditAmount;
    private BigDecimal paymentAmount;
    private BigDecimal amountForReturn;
    private Date creditDate;
    private Date firstPaymentDate;
    private Date lastPaymentDate;
    private BigDecimal lateFineAmount;

    @Version
    private Timestamp version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public BigDecimal getAmountForReturn() {
        return amountForReturn;
    }

    public void setAmountForReturn(BigDecimal amountForReturn) {
        this.amountForReturn = amountForReturn;
    }

    public Date getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
    }

    public Date getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public void setFirstPaymentDate(Date firstPaymentDate) {
        this.firstPaymentDate = firstPaymentDate;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public BigDecimal getLateFineAmount() {
        return lateFineAmount;
    }

    public void setLateFineAmount(BigDecimal lateFineAmount) {
        this.lateFineAmount = lateFineAmount;
    }
}
