package com.example.account.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    @SequenceGenerator(name = "person_id_seq", sequenceName = "PERSON_ID_SEQ", allocationSize = 1)
    private Integer id;
    private String personName;

    @NotNull
    private String nationalCode;
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "fk_account_info"))
    private List<AccountInfo> accountInfos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<AccountInfo> getAccountInfos() {
        return accountInfos;
    }

    public void setAccountInfos(List<AccountInfo> accountInfos) {
        this.accountInfos = accountInfos;
    }
}
