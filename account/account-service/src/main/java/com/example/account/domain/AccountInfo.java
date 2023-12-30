package com.example.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "account_info")
@Getter
@Setter
public class AccountInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_info_id_seq")
    @SequenceGenerator(name = "account_info_id_seq", sequenceName = "ACCOUNT_INFO_ID_SEQ", allocationSize = 1)
    private Integer id;

    private String accountNumber;

    private BigDecimal balance;

    private BigDecimal amount;

    private Integer transferTypeCode;

    private Integer withdrawable;

    private String userName;

    @Version
    private Timestamp version;
}
