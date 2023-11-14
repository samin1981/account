package com.example.account.repository;

import com.example.account.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("select person from Person person where person.deleted != 1")
    List<Person> findAllByDeleted();

    @Query("select person from Person person where person.nationalCode = :nationalCode and person.deleted != 1")
    Optional<Person> findPersonByNationalCode(String nationalCode);
    @Query("select person from Person person join person.accountInfo accountInfo where accountInfo.accountNumber = :accountNumber and person.deleted != 1")
    Person findPersonByAccountInfo(@Param("accountNumber") String accountNumber);
    @Modifying
    @Query("update Person person set person.deleted = 1 where person.id = :id")
    void removePersonById(Integer id);

    @Query("select person.nationalCode from Person person join person.facility facility where " +
            "person.deleted != 1 and facility.amountForReturn != 0 and facility.creditDate < :today")
    List<String> getDebtors(Date today);
}
