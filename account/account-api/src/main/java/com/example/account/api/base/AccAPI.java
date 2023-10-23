package com.example.account.api.base;

import com.example.account.api.account.*;
import com.example.account.api.facility.ConditionForFacilityRequest;
import com.example.account.api.facility.ConditionForFacilityResult;
import com.example.account.api.facility.GetFacilityRequest;
import com.example.account.api.facility.GetFacilityResult;
import com.example.account.api.person.*;
import com.example.account.api.transaction.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping(value = "/transaction/getAllTransactions", produces = MediaType.APPLICATION_JSON)
    GetAllTransactionsResult getAllTransactions(@Valid @RequestBody GetAllTransactionsRequest request);

    @PostMapping(value = "/transaction/getOpenAccountTransactions", produces = MediaType.APPLICATION_JSON)
    GetOpenAccountTransactionsResult getOpenAccountTransactions(@Valid @RequestBody GetOpenAccountTransactionsRequest request);

    @PostMapping(value = "/transaction/getTransactionsBySourceAccountNumber", produces = MediaType.APPLICATION_JSON)
    GetTransactionsBySourceAccountNumberResult getTransactionsBySourceAccountNumber(@Valid @RequestBody GetTransactionsBySourceAccountNumberRequest request);

    @PostMapping(value = "/transaction/getTransactionsByDestAccountNumber", produces = MediaType.APPLICATION_JSON)
    GetTransactionsByDestAccountNumberResult getTransactionsByDestAccountNumber(@Valid @RequestBody GetTransactionsByDestAccountNumberRequest request);

    @PostMapping(value = "/transaction/getTransactionsByTransferDate", produces = MediaType.APPLICATION_JSON)
    GetTransactionsByTransferDateResult getTransactionsByTransferDate(@Valid @RequestBody GetTransactionsByTransferDateRequest request);

    @PostMapping(value = "/transaction/internalTransfer", produces = MediaType.APPLICATION_JSON)
    InternalTransferResult internalTransfer(@Valid @RequestBody InternalTransferRequest request);

    @PostMapping(value = "/transaction/cashDeposit", produces = MediaType.APPLICATION_JSON)
    CashDepositResult cashDeposit(@Valid @RequestBody CashDepositRequest request);

    @PostMapping(value = "/transaction/cashWithdraw", produces = MediaType.APPLICATION_JSON)
    CashWithdrawResult cashWithdraw(@Valid @RequestBody CashWithdrawRequest request);

    @PostMapping(value = "/facility/conditionForFacility", produces = MediaType.APPLICATION_JSON)
    ConditionForFacilityResult conditionForFacility(@Valid @RequestBody ConditionForFacilityRequest request);
    @PostMapping(value = "/facility/getFacility", produces = MediaType.APPLICATION_JSON)
    GetFacilityResult getFacility(GetFacilityRequest request);
}
