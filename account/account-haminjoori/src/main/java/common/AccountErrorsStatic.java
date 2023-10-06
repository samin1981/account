package common;

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
    public static final String ERROR_ACCOUNT_PERSON_NOT_FOUND = "EAP0001";

    public static final String ERROR_ACCOUNT_ACCOUNT_INFO_NOT_FOUND = "EAA0001";

    //</editor-fold>

}
