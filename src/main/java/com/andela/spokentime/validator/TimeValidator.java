package com.andela.spokentime.validator;

import com.andela.spokentime.exception.InvalidTimeFormatException;

import java.util.regex.Pattern;

public class TimeValidator {
    private static final Pattern TIME_PATTERN = Pattern.compile("\\d{2}:\\d{2}");

    private TimeValidator() {}

    public static void validate(String time) {
        if (time == null || !TIME_PATTERN.matcher(time).matches()) {
            throw new InvalidTimeFormatException("Time must be in HH:mm format");
        }

        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new InvalidTimeFormatException("Invalid hour or minute");
        }
    }
}
