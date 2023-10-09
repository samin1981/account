package com.example.account.service;

import com.example.account.api.TransferType;
import com.example.account.api.person.*;
import com.example.account.builder.PersonBuilder;
import com.example.account.domain.AccountInfo;
import com.example.account.domain.Person;
import com.example.account.repository.PersonRepository;
import common.AccountError;
import common.AccountErrorsStatic;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public GetPersonByNationalCodeResult getPersonByNationalCode(GetPersonByNationalCodeRequest request) {

        Person person = personRepository.findPersonByNationalCode(request.getNationalCode()).orElseThrow();
        if (person == null) {
            try {
                logger.log(Level.INFO, "person ba in code melli yaft nashod.");
                throw new AccountError(AccountErrorsStatic.ERROR_ACCOUNT_PERSON_NOT_FOUND);
            } catch (AccountError accountError) {
                accountError.printStackTrace();
            }
        }
        GetPersonByNationalCodeResult result = personMapper(person);

        return result;
    }

    public void addPerson(AddPersonRequest request) {

        Person person = PersonBuilder.getInstance()
                .personName(request.getPersonName())
                .phoneNumber(request.getPhoneNumber())
                .nationalCode(request.getNationalCode())
                .accountInfo(null)
                .build();

        personRepository.save(person);
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
        GetPersonByAccountNumberResult result = personByAccountNumberMapper(person);

        return result;
    }

    private com.example.account.api.person.GetPersonDetailResult personDetailMapper(Person source) {
        com.example.account.api.person.GetPersonDetailResult destination = new com.example.account.api.person.GetPersonDetailResult();

        destination.setId(source.getId());
        destination.setPersonName(source.getPersonName());
        destination.setNationalCode(source.getNationalCode());
        destination.setPhoneNumber(source.getPhoneNumber());
        if (source.getAccountInfo() != null) {
            destination.setAccountInfo(fillAccountInfo(source.getAccountInfo()));
        }

        return destination;
    }

    private com.example.account.api.person.GetPersonByAccountNumberResult personByAccountNumberMapper(Person source) {
        com.example.account.api.person.GetPersonByAccountNumberResult destination = new com.example.account.api.person.GetPersonByAccountNumberResult();

        destination.setId(source.getId());
        destination.setPersonName(source.getPersonName());
        destination.setNationalCode(source.getNationalCode());
        destination.setPhoneNumber(source.getPhoneNumber());
        destination.setAccountInfo(fillAccountInfo(source.getAccountInfo()));

        return destination;
    }

    private GetPersonByNationalCodeResult personMapper(Person source) {
        GetPersonByNationalCodeResult destination = new GetPersonByNationalCodeResult();

        destination.setId(source.getId());
        destination.setPersonName(source.getPersonName());
        destination.setNationalCode(source.getNationalCode());
        destination.setPhoneNumber(source.getPhoneNumber());
        if (source.getAccountInfo() != null) {
            destination.setAccountInfo(fillAccountInfo(source.getAccountInfo()));
        }

        return destination;
    }

    private com.example.account.api.account.AccountInfo fillAccountInfo(AccountInfo source) {

        com.example.account.api.account.AccountInfo destination = new com.example.account.api.account.AccountInfo();

        destination.setId(source.getId());
        destination.setAccountNumber(source.getAccountNumber());
        destination.setAmount(source.getAmount());
        destination.setBalance(source.getBalance());
        destination.setTransferDate(source.getTransferDate());
        destination.setTransferType(TransferType.valueOfCode(source.getTransferTypeCode()));

        return destination;
    }


}
