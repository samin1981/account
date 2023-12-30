package com.example.account.helper;

import com.example.account.api.account.GetAccountInfoByAccountNumberResult;
import com.example.account.api.account.GetAccountInfoDetailResult;
import com.example.account.api.person.GetPersonByAccountNumberResult;
import com.example.account.api.person.GetPersonByNationalCodeResult;
import com.example.account.api.person.GetPersonDetailResult;
import com.example.account.api.transaction.TransactionResult;
import com.example.account.domain.AccountInfo;
import com.example.account.domain.Person;
import com.example.account.domain.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {
    GetPersonDetailResult personDetailMap(Person person);
    GetPersonByNationalCodeResult personByNationalCodeMap(Person person);
    GetPersonByAccountNumberResult personByAccountNumberMap(Person person);
    TransactionResult transactionsMap(Transaction transaction);
    GetAccountInfoDetailResult accountInfoDetailMap(AccountInfo accountInfo);
    GetAccountInfoByAccountNumberResult accountInfoByAccountNumberMap (AccountInfo accountInfo);
}
