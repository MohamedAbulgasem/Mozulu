package com.mohamedabulgasem.mozulu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Mohamed Abulgasem on 2019/04/21.
 */
public final class DateUtils {

    /**
     * This utility class is not instantiable
     */
    private DateUtils() {
    }

    /**
     * Return day in specified format.
     *
     * @param timeStamp UNIX timeStamp
     * @return String representing the day
     */
    public static String getDay(long timeStamp) {
        long milliSeconds = timeStamp * 1000L;
        if (milliSeconds != 0L) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            if (isToday(calendar)) {
                return "Today";
            } else if (isTomorrow(calendar)) {
                return "Tomorrow";
            } else {
                return calendar.getDisplayName(Calendar.DAY_OF_WEEK,
                        Calendar.LONG, Locale.getDefault());
            }
        }
        return "Day";
    }

    /**
     * Return date in specified format.
     *
     * @param timeStamp UNIX timeStamp
     * @return String representing the date
     */
    public static String getWeatherDetailsDate(long timeStamp) {
        long milliSeconds = timeStamp * 1000L;
        if (milliSeconds != 0L) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            String dayOfTheWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK,
                    Calendar.LONG, Locale.getDefault());
            int dayOfTheMonth = calendar.get(Calendar.DAY_OF_MONTH);
            String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            return dayOfTheWeek + ", " + dayOfTheMonth + " " + month;
        }
        return "Day";
    }

    /**
     * Return hour in specified format.
     *
     * @param timeStamp UNIX timeStamp
     * @return String representing the hour
     */
    public static String getHour(long timeStamp) {
        long milliSeconds = timeStamp * 1000L;
        if (milliSeconds != 0L) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            if (isCurrentHour(calendar)) {
                return "Now";
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("HH", Locale.getDefault());
                return formatter.format(calendar.getTime()) + ":00";
            }
        }
        return "Time";
    }

    /**
     * Return time in specified format.
     *
     * @param timeStamp UNIX timeStamp
     * @return String representing the time
     */
    public static String getTime(long timeStamp) {
        long milliSeconds = timeStamp * 1000L;
        if (milliSeconds != 0L) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return formatter.format(calendar.getTime());
        }
        return "Time";
    }

    /**
     * @param calendar calendar
     * @return boolean
     */
    private static boolean isToday(Calendar calendar) {
        return (calendar.get(Calendar.ERA) == Calendar.getInstance().get(Calendar.ERA) &&
                calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR) &&
                calendar.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
    }

    /**
     * @param calendar calendar
     * @return boolean
     */
    private static boolean isTomorrow(Calendar calendar) {
        Calendar tomorrowDateCal = Calendar.getInstance();
        tomorrowDateCal.add(Calendar.DAY_OF_YEAR, +1);
        return (calendar.get(Calendar.YEAR) == tomorrowDateCal.get(Calendar.YEAR)
                && calendar.get(Calendar.DAY_OF_YEAR) == tomorrowDateCal.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * @param calendar Calendar
     * @return boolean
     */
    private static boolean isCurrentHour(Calendar calendar) {
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return calendar.get(Calendar.DAY_OF_WEEK) == currentDay
                && calendar.get(Calendar.HOUR_OF_DAY) == currentHour;
    }

}
