package com.example.account.repository;

import com.example.account.domain.Transaction;
import org.aspectj.weaver.loadtime.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findTransactionsByDestinationAccountNumber(String destinationAccountNumber);
    List<Transaction> findTransactionsBySourceAccountNumber(String accountNumber);
    List<Transaction> findTransactionsByTransferDate(Date transferDate);
//    @Query("select transaction from Transaction transaction where transaction.srcTransferTypeCode between ")
//    Options findTransactionsByTransferDateAndTransferTypeCode(Date transferDate, Integer transferTypeCode);
}
