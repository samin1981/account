package com.example.account.repository;

import com.example.account.domain.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Integer> {

    AccountInfo findAccountInfoByAccountNumber(String accountNumber);



//    @Query("select (select accountInfo from AccountInfo accountInfo where accountInfo.accountNumber = ?1)" +
//            " from AccountInfo accountInfo order by accountInfo.transferDate desc")
//    AccountInfo getByLastTransferDateAndAccountNumber(String accountNumber);
}
