package com.example.account.service;

public enum TransferType {
    DEPOSIT(0),
    WITHDRAW(1);

    public final int code;

    TransferType(int code) {
        this.code = code;
    }

    public static TransferType valueOfCode(int code) {
        for (TransferType transferType : values()) {
            if (transferType.code == code) {
                return transferType;
            }
        }
        return null;
    }
}
