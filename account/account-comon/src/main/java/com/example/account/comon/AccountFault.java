package com.example.account.comon;

import java.io.Serializable;

public class AccountFault implements Serializable {
    String code;
    Object[] params;
    String message;

    public AccountFault(String code, Object[] params, String message) {
        this.message = message;
        this.params = params;
        this.code = code;
    }
}
