package com.example.account.job;

import com.example.account.service.FacilityService;
import com.example.account.service.PersonService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class JobService {
    private final PersonService personService;
    public JobService(PersonService personService) {

        this.personService = personService;
    }
    @Scheduled(cron = "${scheduling.debt.peyment.duedate.job.cron}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void debtPaymentDuedate() throws InterruptedException {
        System.out.println("----------start debtPaymentDuedate job---------------");
        personService.getDebtors(new Date());
        System.out.println("----------  end debtPaymentDuedate job---------------");
    }
}
