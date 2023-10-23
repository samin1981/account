package com.example.account.repository;

import com.example.account.domain.Transaction;
import com.example.account.domain.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findTransactionsByDestinationAccountNumber(String destinationAccountNumber);
    List<Transaction> findTransactionsBySourceAccountNumber(String accountNumber);
    List<Transaction> findTransactionsByTransferDate(Date transferDate);
    @Query("select transaction from Transaction transaction where" +
            " transaction.trackingCode like %:openAccountSign%")
    List<TransactionInfo> getOpenAccountTransactions(@Param("openAccountSign") String openAccountSign);
    @Query("select transaction from Transaction transaction where" +
            " transaction.sourceAccountNumber = :accountNumber" +
            " and transaction.srcTransferTypeCode = 1" +
            " and transaction.transferDate between :beforeDate and :currentDate" +
            " and transaction.srcBalance < :halfAmountForFacility")
    List<Transaction> getByAccountNumberAndTransferDateAndBalance(String accountNumber, Date beforeDate, Date currentDate, BigDecimal halfAmountForFacility);
}
