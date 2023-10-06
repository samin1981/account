package com.example.account.domain;

import javax.persistence.*;

@Entity
@Table(name = "account_info")
public class AccountInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_info_id_seq")
    @SequenceGenerator(name = "account_info_id_seq", sequenceName = "ACCOUNT_INFO_ID_SEQ", allocationSize = 1)
    private Integer id;
    private String accountNumber;

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

}
