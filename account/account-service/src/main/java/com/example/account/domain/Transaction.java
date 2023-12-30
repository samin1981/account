package com.example.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "TRANSACTION_ID_SEQ", allocationSize = 1)
    private Integer id;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private BigDecimal srcBalance;
    private BigDecimal destBalance;
    private Date transferDate;
    private String trackingCode;
    private Integer transactionTypeCode;
    private Integer srcTransferTypeCode;

    @Version
    private Timestamp version;
}
