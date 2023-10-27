package com.example.account.service;

import com.example.account.helper.Mappers;
import com.example.account.api.person.*;
import com.example.account.builder.PersonBuilder;
import com.example.account.comon.AccountErrorsStatic;
import com.example.account.comon.AccountException;
import com.example.account.domain.Person;
import com.example.account.repository.PersonRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PersonService {
    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Autowired
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public GetAllPersonsResult getAllPersons(GetAllPersonsRequest request) {
        GetAllPersonsResult result = new GetAllPersonsResult();

        List<com.example.account.api.person.GetPersonDetailResult> persons = personRepository.findAll().stream().map(this::personsMapper).collect(Collectors.toList());
        if (persons.isEmpty() && persons.size() == 0) {
            throw new AccountException(AccountErrorsStatic.ERROR_ACCOUNT_PERSON_NOT_FOUND);
        }
        result.setItems(persons);
        return result;
    }


    public GetPersonByNationalCodeResult getPersonByNationalCode(GetPersonByNationalCodeRequest request) {

        if (personRepository.findPersonByNationalCode(request.getNationalCode()).isEmpty()) {
            throw new AccountException(AccountErrorsStatic.ERROR_ACCOUNT_PERSON_NOT_FOUND, request.getNationalCode());
        }
        Person person = personRepository.findPersonByNationalCode(request.getNationalCode()).get();
        GetPersonByNationalCodeResult result = new GetPersonByNationalCodeResult();
        result = Mappers.personMapper(person, result);

        return result;
    }

    public void addPerson(AddPersonRequest request) {
        Optional person = personRepository.findPersonByNationalCode(request.getNationalCode());
        if (person != null) {
            //person ghablan dar system sabt shode ast
        }
        Person newPerson = PersonBuilder.getInstance()
                .personName(request.getPersonName())
                .phoneNumber(request.getPhoneNumber())
                .nationalCode(request.getNationalCode())
                .accountInfo(null)
                .build();

        personRepository.save(newPerson);
    }

    public void removePersonByNationalCode(RemovePersonByNationalCodeRequest request) {
        Person person = personRepository.findPersonByNationalCode(request.getNationalCode()).orElseThrow();
        if (person == null) {
            //person yaft nashod
        }
        personRepository.delete(person);
    }

    public GetPersonByAccountNumberResult getPersonByAccountNumber(GetPersonByAccountNumberRequest request) {
        Person person = personRepository.findPersonByAccountInfo(request.getAccountNumber());
        if (person == null) {
            // shakhsi ba in shomare hesab yaft nashod
        }
        GetPersonByAccountNumberResult result = new GetPersonByAccountNumberResult();
        result = Mappers.personMapper(person, result);

        return result;
    }

    private GetPersonDetailResult personsMapper(Person person) {
        GetPersonDetailResult result = new GetPersonDetailResult();
        result = Mappers.personMapper(person, result);

        return result;
    }
}
