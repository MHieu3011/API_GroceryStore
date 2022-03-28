package com.vcc.apigrocerystore.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    private DateTimeUtils() {
    }

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * generate time in yyyy-MM-dd format
     *
     * @param timeInMilliSecs time in milliseconds
     * @return date String in yyyy-MM-dd format
     */
    public static String generateTime(long timeInMilliSecs) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMilliSecs);
        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * generate time in specified format
     *
     * @param timeInMilliSecs time in milliseconds
     * @param datePattern     date pattern, eg: yyyy-MM-dd, yyyy-MM-dd HH:mm:ss,....
     * @return date String in yyyy-MM-dd format
     */
    public static String generateTime(long timeInMilliSecs, String datePattern) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timeInMilliSecs);
        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * get time in milliseconds for a specified date
     *
     * @param date        date in String format, eg: 2020-01-01
     * @param datePattern date pattern, eg: yyyy-MM-dd, yyyy-MM-dd HH:mm:ss,....
     * @return time in milliseconds
     * @throws Exception if any error occurs
     */
    public static long getTimeInMilliSecs(String date, String datePattern) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
        Date dateObject = formatter.parse(date);
        return dateObject.getTime();
    }

    public static long getTimeInSecs(String dt) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        Date date = sdf.parse(dt);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    public static String formatHour(Long hour) {
        if (hour == null) return null;

        StringBuilder sb = new StringBuilder(hour.toString());
        return (hour < 10) ? sb.insert(0, "0").toString() : sb.toString();
    }

    public static String formatTimeInSec(Long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        return sdf.format(calendar.getTime());
    }
}
