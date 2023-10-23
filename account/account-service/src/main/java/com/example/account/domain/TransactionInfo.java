package com.example.account.domain;

import java.math.BigDecimal;
import java.util.Date;

public interface TransactionInfo {
    Integer getId();
    String getDestinationAccountNumber();
    BigDecimal getAmount();
    Date getTransferDate();
}
