package com.example.account.helper;

import com.example.account.api.account.GetAccountInfoByAccountNumberResult;
import com.example.account.api.account.GetAccountInfoDetailResult;
import com.example.account.api.person.GetPersonByAccountNumberResult;
import com.example.account.api.person.GetPersonByNationalCodeResult;
import com.example.account.api.person.GetPersonDetailResult;
import com.example.account.api.transaction.TransferType;
import com.example.account.domain.AccountInfo;
import com.example.account.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        implementationPackage = "com.example.account.helper")
public interface AccountMapper {
    GetPersonDetailResult personsMapperForPersonDetail(Person person);
    GetPersonByNationalCodeResult personsMapperForPersonByNationalCode(Person person);
    GetPersonByAccountNumberResult personMapperForPersonByAccountNumber(Person person);
   @Mapping(source = "transferTypeCode", target = "transferType")
    GetAccountInfoDetailResult accountInfosMapperForAccountInfoDetail(AccountInfo accountInfo);

//    @Mapping(source = "transferTypeCode", target = "transferType")
//    GetAccountInfoByAccountNumberResult accountInfosMapperForAccountInfoByAccountNumber(AccountInfo accountInfo);

    // TODO: ۰۴/۰۱/۲۰۲۴ convert 0 and 1 to code
    @Mapping(source = "0", target = "DEPOSIT")
    @Mapping(source = "1", target = "WITHDRAW")
    default TransferType map(Integer transferTypeCode){
        return TransferType.valueOfCode(transferTypeCode.intValue());
    }
}
