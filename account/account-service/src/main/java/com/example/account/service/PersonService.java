package com.example.account.service;

import com.example.account.api.RemovePersonRequest;
import com.example.account.api.person.AddPersonRequest;
import com.example.account.api.person.GetAllPersonsResult;
import com.example.account.api.person.GetPersonRequest;
import com.example.account.api.person.GetPersonResult;
import com.example.account.builder.PersonBuilder;
import com.example.account.domain.Person;
import com.example.account.repository.PersonRepository;
import common.AccountError;
import common.AccountErrorsStatic;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public GetAllPersonsResult getAllPersons() {

        GetAllPersonsResult result = new GetAllPersonsResult();
        List<com.example.account.api.person.GetPersonDetailResult> persons = personRepository.findAll().stream().map(this::personDetailMapper).collect(Collectors.toList());
        if (persons.isEmpty() && persons.size() == 0) {
            try {
                logger.log(Level.INFO, "person not found.");
                throw new AccountError(AccountErrorsStatic.ERROR_ACCOUNT_PERSON_NOT_FOUND);
            } catch (AccountError accountError) {
                accountError.printStackTrace();
            }
        }
        result.setItems(persons);

        return result;
    }

    public GetPersonResult getPerson(GetPersonRequest request) {

        GetPersonResult result = new GetPersonResult();
        List<Person> persons = personRepository.findByPersonName(request.getPersonName());
        if (persons.isEmpty() && persons.size() == 0) {
            try {
                logger.log(Level.INFO, "person not found.");
                throw new AccountError(AccountErrorsStatic.ERROR_ACCOUNT_PERSON_NOT_FOUND);
            } catch (AccountError accountError) {
                accountError.printStackTrace();
            }
        }
        result.setItems(persons.stream().map(this::personDetailMapper).collect(Collectors.toList()));

        return result;
    }

    public void addPerson(AddPersonRequest request) {

        Person person = PersonBuilder.getInstance()
                .personName(request.getPersonName())
                .phoneNumber(request.getPhoneNumber())
                .nationalCode(request.getNationalCode())
                .build();

        personRepository.save(person);
    }

    public void removePerson(RemovePersonRequest request) {
        Person person = personRepository.findByNationalCode(request.getNationalCode()).orElseThrow();
        personRepository.delete(person);
    }

    private com.example.account.api.person.GetPersonDetailResult personDetailMapper(Person source) {
        com.example.account.api.person.GetPersonDetailResult destination = new com.example.account.api.person.GetPersonDetailResult();

        destination.setId(source.getId());
        destination.setPersonName(source.getPersonName());
        destination.setNationalCode(source.getNationalCode());
        destination.setPhoneNumber(source.getPhoneNumber());

        return destination;
    }
}
