package com.example.account.api.impl;

import com.example.account.api.account.*;
import com.example.account.api.base.AccAPI;
import com.example.account.api.transaction.GetTransactionsByTransferDateRequest;
import com.example.account.api.transaction.GetTransactionsByTransferDateResult;
import com.example.account.api.person.*;
import com.example.account.api.transaction.*;
import com.example.account.service.AccountInfoService;
import com.example.account.service.PersonService;
import com.example.account.service.TransactionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accApi")
public class AccWS implements AccAPI {
    private final PersonService personService;
    private final AccountInfoService accountInfoService;
    private final TransactionService transactionService;

    public AccWS(PersonService personService,
                 AccountInfoService accountInfoService,
                 TransactionService transactionService) {
        this.personService = personService;
        this.accountInfoService = accountInfoService;
        this.transactionService = transactionService;
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
        accountInfoService.openAnAccount(request);
        return new OpenAnAccountResult(true);
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
    public GetFacilityResult getFacility(GetFacilityRequest request) {
        return transactionService.getFacility(request);
    }
}
