package com.example.account.service;

import com.example.account.api.transaction.GetAllTransactionsResult;
import com.example.account.api.transaction.GetTransactionsByAccountNumberRequest;
import com.example.account.api.transaction.GetTransactionsByAccountNumberResult;
import com.example.account.domain.Transaction;
import com.example.account.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public GetAllTransactionsResult getAllTransactions() {
        GetAllTransactionsResult result = new GetAllTransactionsResult();
        List<com.example.account.api.transaction.Transaction> transactions = transactionRepository.findAll().stream().map(this::transactionDetailMapper).collect(Collectors.toList());
        if (transactions.isEmpty() && transactions.size() == 0) {
           //transaction not found
        }
        result.setItems(transactions);

        return result;
    }

    public GetTransactionsByAccountNumberResult getTransactionByAccountNumber(GetTransactionsByAccountNumberRequest request) {
        GetTransactionsByAccountNumberResult result = new GetTransactionsByAccountNumberResult();

        List<Transaction> destTransactions = transactionRepository.findTransactionByAccountInfo_AccountNumber(request.getAccountNumber());
        List<com.example.account.api.transaction.Transaction> transactions = destTransactions.stream().map(this::transactionDetailMapper).collect(Collectors.toList());

        if(transactions.isEmpty() && transactions.size() == 0) {
            //transactions not found
        }
        result.setItems(transactions);

        return result;
    }

    private com.example.account.api.transaction.Transaction transactionDetailMapper(Transaction source) {
        com.example.account.api.transaction.Transaction destination = new com.example.account.api.transaction.Transaction();

        destination.setId(source.getId());
        destination.setAmount(source.getAmount());
        destination.setSourceAccountNumber(source.getSourceAccountNumber());
        destination.setDestinationAccountNumber(source.getDestinationAccountNumber());
        destination.setTransferDate(source.getTransferDate());
        destination.setTrackingCode(source.getTrackingCode());
        destination.setTransactionType(com.example.account.api.TransactionType.valueOfCode(source.getTransactionTypeCode()));

        return destination;
    }
}
