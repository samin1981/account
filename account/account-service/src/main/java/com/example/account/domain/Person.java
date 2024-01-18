package com.example.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "person", uniqueConstraints = {@UniqueConstraint(name = "UK_PERSON_NATIONAL_CODE", columnNames = {"nationalCode"})})
@Getter
@Setter
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
}
