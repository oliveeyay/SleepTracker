/**
 * Copyright 2013 Olivier Goutay (olivierg13)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.og.health.sleeptracker.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by olivier.goutay on 2/18/16.
 */
public final class DateUtilities {

    /**
     * Short hour minute :   9:54 AM
     */
    public static final String DATE_SHORT_HOUR_MINUTE_STRING_FORMAT = "h:mm a";

    /**
     * Short month day hour minute :   Jan 20 - 9:54 AM
     */
    public static final String DATE_SHORT_MONTH_DAY_HOUR_MINUTE_STRING_FORMAT = "LLL d - h:mm a";

    /**
     * Short hour :  9
     */
    public static final String DATE_SHORT_HOUR_STRING_FORMAT = "h";

    private static final long FIVE_MINUTES = 5 * 60 * 1000;

    /**
     * @param date
     * @param
     * @return String in the format passed
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.US);
        return df.format(date);
    }

    /**
     * Return a well formatted description of the dates
     */
    public static String datesToString(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(DATE_SHORT_MONTH_DAY_HOUR_MINUTE_STRING_FORMAT, Locale.US);
        SimpleDateFormat df2 = new SimpleDateFormat(DATE_SHORT_HOUR_MINUTE_STRING_FORMAT, Locale.US);
        return df.format(startDate) + " to " + df2.format(endDate);
    }

    /**
     * Returns the time difference between two dates in {@link TimeUnit#MINUTES}
     *
     * @param startDate The startDate
     * @param endDate   The endDate
     * @return The time difference in {@link TimeUnit#MINUTES}
     */
    public static long getTimeDifferenceInMinutes(Date startDate, Date endDate) {
        if (endDate.getTime() < startDate.getTime()) {
            return getTimeDifferenceInMinutes(endDate, startDate);
        }

        return TimeUnit.MILLISECONDS.toMinutes(endDate.getTime() - startDate.getTime());
    }

    /**
     * Returns the date {@link #FIVE_MINUTES} after the param date
     */
    public static Date getFiveMinutesAfter(Date date) {
        return new Date(date.getTime() + FIVE_MINUTES);
    }

    /**
     * Returns the previous day of the passed {@link Date}
     *
     * @return The date - 24 hours
     */
    public static Date getYesterday(Date date) {
        if (date == null) {
            return getYesterday(new Date());
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_WEEK, -1);

        return new Date(cal.getTimeInMillis());
    }

    /**
     * Returns the date with minus one hour
     */
    public static Date getMinusOneHour(Date date) {
        if (date == null) {
            return getMinusOneHour(new Date());
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, -1);

        return new Date(cal.getTimeInMillis());
    }

}
