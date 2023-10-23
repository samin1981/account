package commons;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class UtilAccount {

    public static String generateAccountNumber() {
        while (true) {
            long numb = (long) (Math.random() * 100000000 * 1000000); // had to use this as int's are to small for a 13 digit number.
            if (String.valueOf(numb).length() == 13)
                return numb + "";
        }
    }

    public static String generateTrackingCode() {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 9) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();

        return saltStr;
    }

    public static Date beforeMonths(int numberOfMonths) {
        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.MONTH, -numberOfMonths);

        return c.getTime();
    }

    public static Date firstOfNextMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static float getAmountForReturn(float amount, float percent) {
        return ((amount * (percent / 100)) + amount);
    }

    public static float getPaymentAmount(float amountForReturn, float numberOfMonth) {
        return amountForReturn / numberOfMonth;
    }

    public static Date getLastPaymentDate(int numberOfMonths) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, numberOfMonths);
        return cal.getTime();
    }

    public static float getLateFineAmountForOneDay(float amountForReturn, float yearlyFinePercent) {
        return ((amountForReturn * yearlyFinePercent) / 100) / 365;
    }
}
