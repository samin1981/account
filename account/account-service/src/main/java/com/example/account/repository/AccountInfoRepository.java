package com.example.account.repository;

import com.example.account.domain.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Integer> {
    AccountInfo findAccountInfoByAccountNumber(String accountNumber);
    @Query("select accountInfo from AccountInfo accountInfo " +
            "join Person person on person.accountInfo = accountInfo.id " +
            "where person.nationalCode = :nationalCode")
    List<AccountInfo> getAccountInfosByNationalcode(String nationalCode);
}
