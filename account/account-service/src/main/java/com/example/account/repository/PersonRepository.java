package com.example.account.repository;

import com.example.account.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

//    @Query("select person from Person person where person.id = ?1")

    List<Person> findByPersonName(String personName);

//    @Query("update Person person set person.phoneNumber = :phoneNumber, person.nationalCode = :nationalCode where person.personName = :personName")
//    Person updatePersonByName(/*@Param("id") Integer id,*/
//            @Param("personName") String personName,
//            @Param("nationalCode") String nationalCode,
//            @Param("phoneNumber") String phoneNumber);

    Optional<Person> findByNationalCode(String nationalCode);
}
