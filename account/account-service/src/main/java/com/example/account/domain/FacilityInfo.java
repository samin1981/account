package com.example.account.domain;

import java.math.BigDecimal;
import java.util.Date;

public interface FacilityInfo {
    BigDecimal getCreditAmount();
    BigDecimal getPaymentAmount();
    Date getCreditDate();
    Date getFirstPaymentDate();

 }
