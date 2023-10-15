package com.example.account.api.transaction;

public class CashWithdrawRequest extends CashRequest {
    private String nationalCode;
    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
