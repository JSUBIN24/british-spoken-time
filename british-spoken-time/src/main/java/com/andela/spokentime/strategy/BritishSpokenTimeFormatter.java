package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.exception.InvalidTimeFormatException;

public class BritishSpokenTimeFormatter implements SpokenTimeFormatter {
    @Override
    public SpokenTimeResponse format(String time) {

        if (time == null || !time.matches("\\d{4}")) {
            throw new InvalidTimeFormatException("Time must be in HHmm 24-hour format");
        }

        String hourStr = time.substring(0, 2);
        String minuteStr = time.substring(2, 4);

        int hour = Integer.parseInt(hourStr);
        int minute = Integer.parseInt(minuteStr);

        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new InvalidTimeFormatException("Invalid hour or minute");
        }

        if (hour == 0 && minute == 0) {
            return new SpokenTimeResponse("midnight");
        }

        if (hour == 12 && minute == 0) {
            return new SpokenTimeResponse("noon");
        }

        if (minute == 0) {
            return new SpokenTimeResponse(String.format("%s o'clock", convertHourToWords(hour)));
        }

        return new SpokenTimeResponse("Not implemented yet");
    }

    private String convertHourToWords(int hour) {
        int displayHour = hour % 12;
        if (displayHour == 0) {
            displayHour = 12;
        }
        String[] numbers = {
                "twelve", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine",
                "ten", "eleven"
        };
        return numbers[displayHour];
    }
}
