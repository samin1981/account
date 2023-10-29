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

    public GetAllPersonsResult getAllPersons() {
        GetAllPersonsResult result = new GetAllPersonsResult();

        List<com.example.account.api.person.GetPersonDetailResult> persons = personRepository.findAllByDeleted().stream().map(Mappers::personsMapper).collect(Collectors.toList());
        if (persons.isEmpty() && persons.size() == 0) {
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, null);
        }
        result.setItems(persons);
        return result;
    }

    public GetPersonByNationalCodeResult getPersonByNationalCode(GetPersonByNationalCodeRequest request) {
        Person existPerson = personRepository.findPersonByNationalCode(request.getNationalCode())
                .orElseThrow(() -> new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, request.getNationalCode()));

        GetPersonByNationalCodeResult result = new GetPersonByNationalCodeResult();
        result = Mappers.personMapper(existPerson, result);

        return result;
    }

    public void addPerson(AddPersonRequest request) {
        Optional person = personRepository.findPersonByNationalCode(request.getNationalCode());
        if (person.isPresent()) {
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_EXIST, request.getNationalCode());
        }

        Person newPerson = PersonBuilder.getInstance()
                .personName(request.getPersonName())
                .phoneNumber(request.getPhoneNumber())
                .nationalCode(request.getNationalCode())
                .deleted(0)
                .accountInfo(null)
                .build();

        personRepository.save(newPerson);
    }

    public void removePersonByNationalCode(RemovePersonByNationalCodeRequest request) {
        Person existPerson = personRepository.findPersonByNationalCode(request.getNationalCode())
                .orElseThrow(() -> new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, request.getNationalCode()));

        personRepository.removePersonById(existPerson.getId());
        personRepository.save(existPerson);
    }

    public GetPersonByAccountNumberResult getPersonByAccountNumber(GetPersonByAccountNumberRequest request) {
        Person person = personRepository.findPersonByAccountInfo(request.getAccountNumber());
        if (person == null) {
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_WITH_ACCOUNT_NUMBER_NOT_FOUND, request.getAccountNumber());
        }
        GetPersonByAccountNumberResult result = new GetPersonByAccountNumberResult();
        result = Mappers.personMapper(person, result);

        return result;
    }


}
