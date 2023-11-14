package com.example.account.api.impl;

import com.example.account.api.account.*;
import com.example.account.api.base.AccAPI;
import com.example.account.api.facility.ConditionForFacilityRequest;
import com.example.account.api.facility.ConditionForFacilityResult;
import com.example.account.api.facility.GetFacilityRequest;
import com.example.account.api.facility.GetFacilityResult;
import com.example.account.api.person.*;
import com.example.account.api.transaction.*;
import com.example.account.service.AccountInfoService;
import com.example.account.service.FacilityService;
import com.example.account.service.PersonService;
import com.example.account.service.TransactionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accApi")
public class AccWS extends BaseWS implements AccAPI {
    private final PersonService personService;
    private final AccountInfoService accountInfoService;
    private final TransactionService transactionService;
    private final FacilityService facilityService;

    public AccWS(PersonService personService,
                 AccountInfoService accountInfoService,
                 TransactionService transactionService,
                 FacilityService facilityService) {

        this.personService = personService;
        this.accountInfoService = accountInfoService;
        this.transactionService = transactionService;
        this.facilityService = facilityService;
    }

    // persons
    @Override
    public GetAllPersonsResult getAllPersons(GetAllPersonsRequest request) {
        return personService.getAllPersons();
    }
    @Override
    public GetPersonByNationalCodeResult getPersonByNationalCode(GetPersonByNationalCodeRequest request) {
        return personService.getPersonByNationalCode(request);
    }
    @Override
    public AddPersonResult addPerson(AddPersonRequest request) {
        personService.addPerson(request);
        return new AddPersonResult(true);
    }
    @Override
    public RemovePersonByNationalCodeResult removePersonByNationalCode(RemovePersonByNationalCodeRequest request) {
        personService.removePersonByNationalCode(request);
        return new RemovePersonByNationalCodeResult(true);
    }
    @Override
    public GetPersonByAccountNumberResult getPersonByAccountNumber(GetPersonByAccountNumberRequest request) {
        return personService.getPersonByAccountNumber(request);
    }
    // AccountInfos
    @Override
    public OpenAnAccountResult openAnAccount(OpenAnAccountRequest request) {
        return accountInfoService.openAnAccount(request);
    }
    @Override
    public GetAllAccountInfosResult getAllAccountInfos(GetAllAccountInfosRequest request) {
        return accountInfoService.getAllAccountInfos();
    }
    @Override
    public GetAccountInfoByAccountNumberResult getAccountInfoByAccountNumber(GetAccountInfoByAccountNumberRequest request) {
        return accountInfoService.getAccountInfoByAccountNumber(request);
    }
    // Transactions
    @Override
    public GetAllTransactionsResult getAllTransactions(GetAllTransactionsRequest request) {
        return transactionService.getAllTransactions();
    }
    @Override
    public GetOpenAccountTransactionsResult getOpenAccountTransactions(GetOpenAccountTransactionsRequest request) {
        return transactionService.getOpenAccountTransactions(request);
    }
    @Override
    public GetTransactionsBySourceAccountNumberResult getTransactionsBySourceAccountNumber(GetTransactionsBySourceAccountNumberRequest request) {
        return transactionService.getTransactionsBySourceAccountNumber(request);
    }
    @Override
    public GetTransactionsByDestAccountNumberResult getTransactionsByDestAccountNumber(GetTransactionsByDestAccountNumberRequest request) {
        return transactionService.getTransactionsByDestAccountNumber(request);
    }
    @Override
    public GetTransactionsByTransferDateResult getTransactionsByTransferDate(GetTransactionsByTransferDateRequest request) {
        return transactionService.getTransactionsByTransferDate(request);
    }
    @Override
    public InternalTransferResult internalTransfer(InternalTransferRequest request) {
        return transactionService.internalTransfer(request);
    }
    @Override
    public CashDepositResult cashDeposit(CashDepositRequest request) {
        return transactionService.cashDeposit(request);
    }
    @Override
    public CashWithdrawResult cashWithdraw(CashWithdrawRequest request) {
        return transactionService.cashWithdraw(request);
    }
    @Override
    public ConditionForFacilityResult conditionForFacility(ConditionForFacilityRequest request) {
        facilityService.conditionForFacility(request);
        return new ConditionForFacilityResult(true);
    }
    @Override
    public GetFacilityResult getFacility(GetFacilityRequest request) {
        return facilityService.getFacility(request);
    }
}
