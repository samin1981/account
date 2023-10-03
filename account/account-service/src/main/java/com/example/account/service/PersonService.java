package com.example.account.service;

import com.example.account.api.person.AddPersonRequest;
import com.example.account.domain.Person;
import com.example.account.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public com.example.account.api.person.GetAllPersonsResult getAllPersons() {

        com.example.account.api.person.GetAllPersonsResult result = new com.example.account.api.person.GetAllPersonsResult();
        List<com.example.account.api.person.Person> persons = personRepository.findAll().stream().map(this::mapServiceUserToApiUser).collect(Collectors.toList());
        result.setItems(persons);

        return result;
    }

  /*  public Person getUser(Integer id) {
        Person user = mapServiceUserToApiUser(userRepository.getById(id));
        return user;
    }

    public void addUser(AddUserRequest request) {
        Person user = mapApiUserToServiceUser(request);
        userRepository.save(user);
    }

    public void updateUser(UpdateUserRequest request) {
        Person user = userRepository.getByIdAndPersonName(request.getId(), request.getUserName());
        userRepository.updatePersonByName(user.getPersonName(), request.getNationalCode(), request.getPhoneNumber());
    }

    public void removeUser(Integer id) {
        Person user = userRepository.getById(id);
        userRepository.delete(user);
    }
*/
    private com.example.account.api.person.Person mapServiceUserToApiUser(com.example.account.domain.Person source) {
        com.example.account.api.person.Person destination = new com.example.account.api.person.Person();

        destination.setId(source.getId());
        destination.setPersonName(source.getPersonName());
        destination.setNationalCode(source.getNationalCode());
        destination.setPhoneNumber(source.getPhoneNumber());

        return destination;
    }

    private Person mapApiUserToServiceUser(AddPersonRequest source) {
        Person destination = new Person();

        destination.setPersonName(source.getPersonName());
        destination.setNationalCode(source.getNationalCode());
        destination.setPhoneNumber(source.getPhoneNumber());

        return destination;
    }


}
