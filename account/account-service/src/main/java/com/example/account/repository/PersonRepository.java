package com.example.account.repository;

import com.example.account.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findPersonByNationalCode(String nationalCode);
    @Query("select person from Person person join person.accountInfo accountInfo where accountInfo.accountNumber = :accountNumber")
    Person findPersonByAccountInfo(@Param("accountNumber") String accountNumber);
}
