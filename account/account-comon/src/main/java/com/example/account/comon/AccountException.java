package com.example.account.comon;

import java.util.Locale;
import java.util.ResourceBundle;

public class AccountException  extends RuntimeException {
    static Locale locale = new Locale("en", "fa");
    static ResourceBundle labels = ResourceBundle.getBundle("account-errors_en", locale);
    private AccountFault accountFault;

    public AccountException(){

    }
    public AccountException(String code, Object... params) {
        String message = getMessage(code);
        this.accountFault = new AccountFault(code, params, message);
    }
    public AccountException(String message) {
        super(message);
    }
    public String getMessage(String code) {
        return labels.getString(code);
    }

    public AccountFault getAccountFault() {
        return accountFault;
    }
}

