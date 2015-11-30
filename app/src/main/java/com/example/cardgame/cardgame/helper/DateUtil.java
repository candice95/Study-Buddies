package com.example.cardgame.cardgame.helper;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenshiyu on 11/29/15.
 */
public class DateUtil {

    public static long getDiffInDays(String month, String day) {
        int dayInt = Integer.parseInt(day);
        Calendar aptDate = Calendar.getInstance();
        switch (month) {
            case "Jan":
                aptDate.set(2015, Calendar.JANUARY, dayInt);
                break;
            case "Feb":
                aptDate.set(2015, Calendar.FEBRUARY, dayInt);
                break;
            case "Mar":
                aptDate.set(2015, Calendar.MARCH, dayInt);
                break;
            case "Apr":
                aptDate.set(2015, Calendar.APRIL, dayInt);
                break;
            case "May":
                aptDate.set(2015, Calendar.MAY, dayInt);
                break;
            case "Jun":
                aptDate.set(2015, Calendar.JUNE, dayInt);
                break;
            case "Jul":
                aptDate.set(2015, Calendar.JULY, dayInt);
                break;
            case "Aug":
                aptDate.set(2015, Calendar.AUGUST, dayInt);
                break;
            case "Sep":
                aptDate.set(2015, Calendar.SEPTEMBER, dayInt);
                break;
            case "Oct":
                aptDate.set(2015, Calendar.OCTOBER, dayInt);
                break;
            case "Nov":
                aptDate.set(2015, Calendar.NOVEMBER, dayInt);
                break;
            case "Dec":
                aptDate.set(2015, Calendar.DECEMBER, dayInt);
                break;
            default:
                break;
        }
        return TimeUnit.MILLISECONDS.toDays(aptDate.getTimeInMillis()) - TimeUnit.MILLISECONDS.toDays(Calendar.getInstance().getTimeInMillis());
    }
}
