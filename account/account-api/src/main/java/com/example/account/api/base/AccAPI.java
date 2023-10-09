package com.example.account.api.base;

import com.example.account.api.account.*;
import com.example.account.api.person.*;
import com.example.account.api.transaction.GetAllTransactionsRequest;
import com.example.account.api.transaction.GetAllTransactionsResult;
import com.example.account.api.transaction.GetTransactionsByAccountNumberRequest;
import com.example.account.api.transaction.GetTransactionsByAccountNumberResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

public interface AccAPI {

    @PostMapping(value = "/person/getAllPersons", produces = MediaType.APPLICATION_JSON)
    GetAllPersonsResult getAllPersons(@Valid @RequestBody GetAllPersonsRequest request);

    @PostMapping(value = "/person/getPersonByNationalCode", produces = MediaType.APPLICATION_JSON)
    GetPersonByNationalCodeResult getPersonByNationalCode(@Valid @RequestBody GetPersonByNationalCodeRequest request);

    @PostMapping(value = "/person/addPerson", produces = MediaType.APPLICATION_JSON)
    AddPersonResult addPerson(@Valid @RequestBody AddPersonRequest request);

    @PostMapping(value = "/person/removePersonByNationalCode", produces = MediaType.APPLICATION_JSON)
    RemovePersonByNationalCodeResult removePersonByNationalCode(@Valid @RequestBody RemovePersonByNationalCodeRequest request);

    @PostMapping(value = "/person/getPersonByAccountNumber", produces = MediaType.APPLICATION_JSON)
    GetPersonByAccountNumberResult getPersonByAccountNumber(@Valid @RequestBody GetPersonByAccountNumberRequest request);

    @PostMapping(value = "/account/openAnAccount", produces = MediaType.APPLICATION_JSON)
    OpenAnAccountResult openAnAccount(@Valid @RequestBody OpenAnAccountRequest request);

    @PostMapping(value = "/account/getAllAccountInfos", produces = MediaType.APPLICATION_JSON)
    GetAllAccountInfosResult getAllAccountInfos(@Valid @RequestBody GetAllAccountInfosRequest request);

    @PostMapping(value = "/account/getAccountInfoByAccountNumber", produces = MediaType.APPLICATION_JSON)
    GetAccountInfoByAccountNumberResult getAccountInfoByAccountNumber(@Valid @RequestBody GetAccountInfoByAccountNumberRequest request);

    @PostMapping(value = "/account/getAllTransactions", produces = MediaType.APPLICATION_JSON)
    GetAllTransactionsResult getAllTransactions(@Valid @RequestBody GetAllTransactionsRequest request);

    @PostMapping(value = "/account/getTransactionsByAccountNumber", produces = MediaType.APPLICATION_JSON)
    GetTransactionsByAccountNumberResult getTransactionsByAccountNumber(@Valid @RequestBody GetTransactionsByAccountNumberRequest request);

    @PostMapping(value = "/account/internalTransfer", produces = MediaType.APPLICATION_JSON)
    InternalTransferResult internalTransfer(@Valid @RequestBody InternalTransferRequest request);

    @PostMapping(value = "/account/cashDeposit", produces = MediaType.APPLICATION_JSON)
    CashDepositResult cashDeposit(@Valid @RequestBody CashDepositRequest request);

    @PostMapping(value = "/account/cashWithdraw", produces = MediaType.APPLICATION_JSON)
    CashWithdrawResult cashWithdraw(@Valid @RequestBody CashWithdrawRequest request);
}
