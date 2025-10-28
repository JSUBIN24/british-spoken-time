package com.andela.spokentime.util;

public final class TimeUtils {
    private TimeUtils() {}

    public static int to12Hour(int hour24) {
        return hour24 % 12 == 0 ? 12 : hour24 % 12;
    }
}
