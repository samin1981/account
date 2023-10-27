package com.example.account.service;

import com.example.account.api.facility.GetFacilityRequest;
import com.example.account.api.facility.GetFacilityResult;
import com.example.account.api.facility.ConditionForFacilityRequest;
import com.example.account.api.facility.GetFacilityResultItem;
import com.example.account.builder.FacilityBuilder;
import com.example.account.comon.UtilAccount;
import com.example.account.domain.*;
import com.example.account.repository.AccountInfoRepository;
import com.example.account.repository.FacilityRepository;
import com.example.account.repository.PersonRepository;
import com.example.account.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class FacilityService {
    private final FacilityRepository facilityRepository;
    private final PersonRepository personRepository;
    private final AccountInfoRepository accountInfoRepository;
    private final TransactionRepository transactionRepository;

    @Value("${amount.for.facility}")
    private float amountForFacility;

    @Value("${percent.for.facility}")
    private float percent;

    @Value("${number.of.months}")
    private int numberOfMonths;

    @Value("${yearly.fine.percent}")
    private float yearlyFineAmount;

    public FacilityService(FacilityRepository facilityRepository,
                           PersonRepository personRepository,
                           AccountInfoRepository accountInfoRepository,
                           TransactionRepository transactionRepository) {

        this.facilityRepository = facilityRepository;
        this.personRepository = personRepository;
        this.accountInfoRepository = accountInfoRepository;
        this.transactionRepository = transactionRepository;
    }

    public void conditionForFacility(ConditionForFacilityRequest request) {
        Person person = personRepository.findPersonByNationalCode(request.getNationalCode()).orElseThrow();
        if (person == null) {
            // shakhs dar system vojood nadarad
        }
        List<AccountInfo> accountInfos = accountInfoRepository.getAccountInfosByNationalcode(person.getNationalCode());
        if (accountInfos == null) {
            //shomare hesabi yaft nashod
        }

        float halfAmountForFacility = amountForFacility / 2;
        Optional<AccountInfo> suitableAccountInfo = accountInfos.stream().filter(c -> c.getBalance().compareTo(BigDecimal.valueOf(halfAmountForFacility)) > 0).findFirst();
        if (suitableAccountInfo.isEmpty()) {
            // shomare hesab sharayete akhz vam ra be dalile mande hesab nadarad
        }
        AccountInfo accountInfoWithSuitableBalance = suitableAccountInfo.get();

        Date threeMonthAgo = UtilAccount.beforeMonths(3);
        List<Transaction> transaction = transactionRepository.getByAccountNumberAndTransferDateAndBalance(accountInfoWithSuitableBalance.getAccountNumber(),
                threeMonthAgo, new Date(), BigDecimal.valueOf(halfAmountForFacility));
        if (transaction != null) {
//             in shakhs be dalile bardasht az hesab mojaz be gereftane vam nemibashad
        }
    }

    public GetFacilityResult getFacility(GetFacilityRequest request) {
        GetFacilityResult result = new GetFacilityResult();

        AccountInfo accountInfo = accountInfoRepository.findAccountInfoByAccountNumber(request.getFacilityAccountNumber());
        if (accountInfo == null) {
            //hesabi baraye akhz vam eftetah nashode ast
        }

        Person person = personRepository.findPersonByAccountInfo(accountInfo.getAccountNumber());
        if (person == null) {
            //
        }

        float amountForReturn = UtilAccount.getAmountForReturn(amountForFacility, percent);
        Facility facility = FacilityBuilder.getInstance()
                .creditAmount(BigDecimal.valueOf(amountForFacility))
                .amountForReturn(BigDecimal.valueOf(amountForReturn))
                .creditDate(new Date())
                .paymentAmount(BigDecimal.valueOf(UtilAccount.getPaymentAmount(amountForReturn, numberOfMonths)))
                .firstPaymentDate(UtilAccount.firstOfNextMonth())
                .lastPaymentDate(UtilAccount.getLastPaymentDate(numberOfMonths))
                .lateFineAmount(BigDecimal.valueOf(UtilAccount.getLateFineAmountForOneDay(amountForReturn, yearlyFineAmount)))
                .build();

        facilityRepository.save(facility);

        person.setFacility(facility);
        personRepository.save(person);

        List<FacilityInfo> customFacility = facilityRepository.getFacilitiesByNationalCode(person.getNationalCode());
        List<GetFacilityResultItem> items = customFacility.stream().map(c -> {
            GetFacilityResultItem item = new GetFacilityResultItem();
            item.setCreditAmount(c.getCreditAmount());
            item.setCreditDate(c.getCreditDate());
            item.setPaymentAmount(c.getPaymentAmount());
            item.setFirsPaymentDate(c.getFirstPaymentDate());
            return item;
        }).collect(Collectors.toList());

        result.setItems(items);
        return result;
    }
}
