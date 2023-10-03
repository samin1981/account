package com.example.account.repository;

import com.example.account.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("select person from Person person where person.id = ?1 and person.personName = ?2")
    Person getByIdAndPersonName(Integer id, String personName);

    @Query("update Person person set person.phoneNumber = :phoneNumber, person.nationalCode = :nationalCode where person.personName = :personName")
    Person updatePersonByName(/*@Param("id") Integer id,*/
            @Param("personName") String personName,
            @Param("nationalCode") String nationalCode,
            @Param("phoneNumber") String phoneNumber);

    @Override
    void delete(Person person);
}
