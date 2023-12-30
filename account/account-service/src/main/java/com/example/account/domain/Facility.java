package com.example.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "facility")
@Getter
@Setter
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
}
