package com.brsoftech.core_utils.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 16/6/16</p>
 * <p>Time: 1:48 PM</p>
 * <p>Project: starterpack</p>
 */
public class SimpleDateUtils {
    private static final String dd_MM_yyyy = "dd/MM/yyyy";
    private static final String HH_mm = "HH:mm";
    private static final String HH_mm_aa = "HH:mm a";

    private static final String TAG = SimpleDateUtils.class.getSimpleName();

    /**
     * <p>Easily check if a particular date exists between two dates or not.</p>
     * <p>This method gives you freedom to provide any sort of date comparison using date objects.</p>
     *
     * @param dateStart starting date object
     * @param dateEnd   ending date object
     * @param dateCurr  current date object
     * @return <p><b>true</b> if date is in between two provided dates, else <b>false</b></p>
     */
    public static boolean isDateInBetween(Date dateStart, Date dateEnd, Date dateCurr) {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(dateStart);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(dateEnd);

        Calendar calendarCurr = Calendar.getInstance();
        calendarCurr.setTime(dateCurr);

        if (calendarCurr.compareTo(calendarStart) == 0 || calendarCurr.compareTo(calendarEnd) == 0) {
            return true;
        } else {
            return calendarCurr.compareTo(calendarStart) == 1 && calendarCurr.compareTo(calendarEnd) == -1;
        }
    }

    public static String getDate(String format, Date date) {
        return new SimpleDateFormat(format, Locale.US).format(date);
    }

    @Deprecated
    public static String getTimeDifference(String startTime, String endTime) {
        try {
            Date dateStart = new SimpleDateFormat(HH_mm_aa).parse(startTime);
            Calendar calendarStart = Calendar.getInstance();
            calendarStart.setTime(dateStart);
            Date dateEnd = new SimpleDateFormat(HH_mm_aa).parse(endTime);
            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(dateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    // test
    public void usageHours() {
        try {
            Date dateStart = new SimpleDateFormat(HH_mm).parse("16:00");
            Date dateEnd = new SimpleDateFormat(HH_mm).parse("22:00");
            Date dateCurr = new SimpleDateFormat(HH_mm).parse("21:00");
            Log.e(TAG, "usageHours-isDateInBetween: " + isDateInBetween(dateStart, dateEnd, dateCurr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // test
    public void usageDays() {
        try {
            Date dateStart = new SimpleDateFormat(dd_MM_yyyy).parse("16/06/2016");
            Date dateEnd = new SimpleDateFormat(dd_MM_yyyy).parse("25/06/2016");
            Date dateCurr = new SimpleDateFormat(dd_MM_yyyy).parse("17/06/2016");
            Log.e(TAG, "usageHours-isDateInBetween: " + isDateInBetween(dateStart, dateEnd, dateCurr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
