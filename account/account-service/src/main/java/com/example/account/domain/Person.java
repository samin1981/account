package com.example.account.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(name = "UK_PERSON_NATIONAL_CODE", columnNames = {"nationalCode"})})
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    @SequenceGenerator(name = "person_id_seq", sequenceName = "PERSON_ID_SEQ", allocationSize = 1)
    private Integer id;

    private String personName;

    @Column(nullable = false)
    private String nationalCode;

    private String phoneNumber;

    private Integer deleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_info_id", foreignKey = @ForeignKey(name = "fk_account_info"))
    private AccountInfo accountInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_id", foreignKey = @ForeignKey(name = "fk_facility"))
    private Facility facility;

    @Version
    private Timestamp version;

    public Person() {}

    public Person(Integer id, String personName, String nationalCode, int i) {
        this.id = id;
        this.personName = personName;
        this.nationalCode = nationalCode;
    }

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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }
}
