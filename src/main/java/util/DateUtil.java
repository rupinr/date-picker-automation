package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date parseInputDate(String date) {
        String inputDateFormatString = "dd/MM/yyyy";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputDateFormatString);
        Date inputDate = null;
        try {
            inputDate = inputDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inputDate;
    }

    public static long daysBetween(Calendar firstDate, Calendar secondDate) {
        return  ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
    }

    public static Date parseDisplayDate(String date) {
        if(date.equals("Today")) {
            return new Date();
        }
        String inputDateFormatString = "EEE, MMM dd";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputDateFormatString);
        Date inputDate = null;
        try {
            inputDate = inputDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inputDate;
    }
}
