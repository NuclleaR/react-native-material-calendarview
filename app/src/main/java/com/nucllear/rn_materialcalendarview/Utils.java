package com.nucllear.rn_materialcalendarview;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;

import java.util.Calendar;

public class Utils {
    static int getDayOfWeekFromString(String dayOfWeek) {
        switch (dayOfWeek) {
            case "monday":
                return Calendar.MONDAY;
            case "tuesday":
                return Calendar.TUESDAY;
            case "wednesday":
                return Calendar.WEDNESDAY;
            case "thursday":
                return Calendar.THURSDAY;
            case "friday":
                return Calendar.FRIDAY;
            case "saturday":
                return Calendar.SATURDAY;
            case "sunday":
                return Calendar.SUNDAY;
            default:
                throw new JSApplicationIllegalArgumentException("Unknown day of week: " + dayOfWeek);
        }
    }
}
