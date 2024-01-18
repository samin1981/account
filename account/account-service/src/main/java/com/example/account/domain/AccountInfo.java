package com.example.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "account_info")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTransferTypeCode() {
        return transferTypeCode;
    }

    public void setTransferTypeCode(Integer transferTypeCode) {
        this.transferTypeCode = transferTypeCode;
    }

    public Integer getWithdrawable() {
        return withdrawable;
    }

    public void setWithdrawable(Integer withdrawable) {
        this.withdrawable = withdrawable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
