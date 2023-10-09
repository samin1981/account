package com.example.account.api;

public enum TransactionType {
    INTERNAL(0),
    CASH(1);

    public final int code;

    TransactionType(int code) {
        this.code = code;
    }

    public static TransactionType valueOfCode(int code) {
        for (TransactionType transactionType : values()) {
            if (transactionType.code == code) {
                return transactionType;
            }
        }
        return null;
    }
}
