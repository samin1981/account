package com.example.account.helper;

import com.example.account.api.person.GetPersonDetailResult;
import com.example.account.domain.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        implementationPackage = "com.example.account.helper")
public interface AccountMapper {
    GetPersonDetailResult personsMapper(Person person);
}
