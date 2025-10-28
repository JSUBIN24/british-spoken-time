package com.andela.spokentime.validator;

import com.andela.spokentime.exception.InvalidTimeFormatException;

import java.util.regex.Pattern;

public class TimeValidator {
    private static final Pattern TIME_PATTERN = Pattern.compile("\\d{4}");

    private TimeValidator() {}

    public static void validate(String time) {
        if (time == null || !TIME_PATTERN.matcher(time).matches()) {
            throw new InvalidTimeFormatException("Time must be in HHmm format");
        }

        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));

        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new InvalidTimeFormatException("Invalid hour or minute");
        }
    }
}
