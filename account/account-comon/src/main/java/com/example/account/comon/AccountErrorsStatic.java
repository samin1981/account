package com.example.account.comon;

public class AccountErrorsStatic {
    private AccountErrorsStatic() {
        throw new IllegalStateException("Utility class");
    }

    //<editor-fold desc="General Errors">
    public static final String ERROR_ACCOUNT_INTERNAL = "EGA0001";
    public static final String ERROR_ACCOUNT_INVALID = "EGA0002";
    public static final String ERROR_ACCOUNT_INVALID_PAGINATION = "EGA0003";
    public static final String ERROR_ACCOUNT_INVALID_PARAMETER = "EGA0004";
    public static final String ERROR_ACCOUNT_SERVICE_ERROR = "EGA0005";
    //</editor-fold>

    //<editor-fold desc="Business Errors">
    public static final String ERROR_PERSON_NOT_FOUND = "EP0001";
    public static final String ERROR_PERSON_EXIST = "EP0002";
    public static final String ERROR_PERSON_WITH_ACCOUNT_NUMBER_NOT_FOUND = "EP0003";
    public static final String ERROR_PERSON_WITH_ACCOUNT_NUMBER_EXIST = "EP0004";

    public static final String ERROR_ACCOUNT_INFO_NOT_FOUND = "EA0001";
    public static final String ERROR_MIN_AMOUNT_NECESSARY = "EA0002";
    public static final String ERROR_SRC_ACCOUNT_NUMBER_NOT_FOUND = "EA0003";
    public static final String ERROR_ACCOUNT_DOES_NOT_BELONG_PERSON = "EA0004";
    public static final String ERROR_INSUFFICIENT_BALANCE = "EA0005";
    public static final String ERROR_DEST_ACCOUNT_INFO_NOT_FOUND = "EA0006";

    public static final String ERROR_ACCOUNT_CAN_NOT_GET_FACILITY_BECAUSE_OF_BALANCE = "EF0001";
    public static final String ERROR_ACCOUNT_CAN_NOT_GET_FACILITY_BECAUSE_OF_WITHDRAW = "EF0002";
    public static final String ERROR_ACCOUNT_IS_NOT_OPEN_FOR_GET_FACILITY = "EF0003";

    public static final String ERROR_TRANSACTION_NOT_FOUND = "ET0001";
    public static final String ERROR_AMOUNT_CAN_NOT_BE_ZERO = "ET0002";

    //</editor-fold>
}
