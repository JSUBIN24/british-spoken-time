package com.andela.spokentime.util;

import java.util.Map;

public final class NumberWordMapperUtil {

    private NumberWordMapperUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    private static final Map<Integer, String> HOURS = Map.ofEntries(
            Map.entry(1, "one"),
            Map.entry(2, "two"),
            Map.entry(3, "three"),
            Map.entry(4, "four"),
            Map.entry(5, "five"),
            Map.entry(6, "six"),
            Map.entry(7, "seven"),
            Map.entry(8, "eight"),
            Map.entry(9, "nine"),
            Map.entry(10, "ten"),
            Map.entry(11, "eleven"),
            Map.entry(12, "twelve")
    );

    private static final Map<Integer, String> MINUTES = Map.<Integer, String>ofEntries(
            Map.entry(1, "one"),
            Map.entry(2, "two"),
            Map.entry(3, "three"),
            Map.entry(4, "four"),
            Map.entry(5, "five"),
            Map.entry(6, "six"),
            Map.entry(7, "seven"),
            Map.entry(8, "eight"),
            Map.entry(9, "nine"),
            Map.entry(10, "ten"),
            Map.entry(11, "eleven"),
            Map.entry(12, "twelve"),
            Map.entry(13, "thirteen"),
            Map.entry(14, "fourteen"),
            Map.entry(15, "fifteen"),
            Map.entry(16, "sixteen"),
            Map.entry(17, "seventeen"),
            Map.entry(18, "eighteen"),
            Map.entry(19, "nineteen"),
            Map.entry(20, "twenty"),
            Map.entry(21, "twenty one"),
            Map.entry(22, "twenty two"),
            Map.entry(23, "twenty three"),
            Map.entry(24, "twenty four"),
            Map.entry(25, "twenty five"),
            Map.entry(26, "twenty six"),
            Map.entry(27, "twenty seven"),
            Map.entry(28, "twenty eight"),
            Map.entry(29, "twenty nine"),
            Map.entry(30, "thirty"),
            Map.entry(31, "thirty one"),
            Map.entry(32, "thirty two"),
            Map.entry(33, "thirty three"),
            Map.entry(34, "thirty four"),
            Map.entry(35, "thirty five"),
            Map.entry(36, "thirty six"),
            Map.entry(37, "thirty seven"),
            Map.entry(38, "thirty eight"),
            Map.entry(39, "thirty nine"),
            Map.entry(40, "forty"),
            Map.entry(41, "forty one"),
            Map.entry(42, "forty two"),
            Map.entry(43, "forty three"),
            Map.entry(44, "forty four"),
            Map.entry(45, "forty five"),
            Map.entry(46, "forty six"),
            Map.entry(47, "forty seven"),
            Map.entry(48, "forty eight"),
            Map.entry(49, "forty nine"),
            Map.entry(50, "fifty"),
            Map.entry(51, "fifty one"),
            Map.entry(52, "fifty two"),
            Map.entry(53, "fifty three"),
            Map.entry(54, "fifty four"),
            Map.entry(55, "fifty five"),
            Map.entry(56, "fifty six"),
            Map.entry(57, "fifty seven"),
            Map.entry(58, "fifty eight"),
            Map.entry(59, "fifty nine")
    );

    public static String toHourWord(int hour) {
        return HOURS.getOrDefault(hour, "unknown");
    }

    public static String toMinuteWord(int minute) {
        return MINUTES.getOrDefault(minute, "unknown");
    }

}
