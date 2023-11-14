package com.example.account.job;

import com.example.account.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class JobService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PersonService personService;
    public JobService(PersonService personService) {

        this.personService = personService;
    }
    @Scheduled(cron = "${scheduling.debt.peyment.duedate.job.cron}")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void debtPaymentDuedate() throws InterruptedException {
        logger.info("----------Start debt payment duedate job---------------");
        personService.getDebtors(new Date());
        logger.info("----------  End debt payment duedate job---------------");
    }
}
