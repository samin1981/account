package com.example.account.service;

import com.example.account.api.person.*;
import com.example.account.builder.PersonBuilder;
import com.example.account.comon.AccountErrorsStatic;
import com.example.account.comon.AccountException;
import com.example.account.domain.Person;
import com.example.account.helper.AccountMapper;
import com.example.account.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PersonService {
    private static final Logger logger = LogManager.getLogger(PersonService.class);
    private final PersonRepository personRepository;
    private final AccountMapper accountMapper;

    public PersonService(PersonRepository personRepository,
                         AccountMapper accountMapper) {
        this.personRepository = personRepository;
        this.accountMapper = accountMapper;
    }

    public GetAllPersonsResult getAllPersons() {
        GetAllPersonsResult result = new GetAllPersonsResult();

        List<com.example.account.api.person.GetPersonDetailResult> persons = personRepository.findAllByDeleted().stream()
                .map(person -> accountMapper.personsMapperForPersonDetail(person)).collect(Collectors.toList());
        if (persons.isEmpty() && persons.size() == 0) {
            logger.error("Person not found");
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, null);
        }
        result.setItems(persons);
        return result;
    }

    public GetPersonByNationalCodeResult getPersonByNationalCode(GetPersonByNationalCodeRequest request) {
        Person existPerson = personRepository.findPersonByNationalCode(request.getNationalCode())
                .orElseThrow(() -> new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, request.getNationalCode()));

        return accountMapper.personsMapperForPersonByNationalCode(existPerson);
    }

    public void addPerson(AddPersonRequest request) {
        Optional person = personRepository.findPersonByNationalCode(request.getNationalCode());
        if (person.isPresent()) {
            logger.error("Person with national code {} exists.", request.getNationalCode());
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
        logger.info("New person with national code {} created" , request.getNationalCode());
    }

    public void removePersonByNationalCode(RemovePersonByNationalCodeRequest request) {
        Person existPerson = personRepository.findPersonByNationalCode(request.getNationalCode())
                .orElseThrow(() -> new AccountException(AccountErrorsStatic.ERROR_PERSON_NOT_FOUND, request.getNationalCode()));

        personRepository.removePersonById(existPerson.getId());
        personRepository.save(existPerson);
        logger.info("person {0} was deleted" , existPerson.getNationalCode());
    }

    public GetPersonByAccountNumberResult getPersonByAccountNumber(GetPersonByAccountNumberRequest request) {
        Person person = personRepository.findPersonByAccountInfo(request.getAccountNumber());
        if (person == null) {
            logger.info("Person with account number {} not found", request.getAccountNumber());
            throw new AccountException(AccountErrorsStatic.ERROR_PERSON_WITH_ACCOUNT_NUMBER_NOT_FOUND, request.getAccountNumber());
        }

        return accountMapper.personMapperForPersonByAccountNumber(person);
    }

    public void getDebtors(Date date) {
        List<String> persons = personRepository.getDebtors(date);
        sendSms(persons);
    }

    private void sendSms(List<String> persons) {
        System.out.println("sms....");
    }

}
