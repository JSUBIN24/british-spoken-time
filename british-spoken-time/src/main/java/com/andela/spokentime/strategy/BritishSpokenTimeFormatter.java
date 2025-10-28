package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.exception.InvalidTimeFormatException;
import com.andela.spokentime.util.NumberWordMapper;

import java.util.Set;

public class BritishSpokenTimeFormatter implements SpokenTimeFormatter {

    private static final Set<Integer> idiomaticPast = Set.of(5, 10, 15, 20, 25);
    private static final Set<Integer> idiomaticTo = Set.of(35, 40, 45, 50, 55);

    @Override
    public SpokenTimeResponse format(String time) {
        if (time == null || !time.matches("\\d{4}")) {
            throw new InvalidTimeFormatException("Time must be in HHmm format");
        }

        int hour24 = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));

        if (hour24 < 0 || hour24 > 23 || minute < 0 || minute > 59) {
            throw new InvalidTimeFormatException("Invalid hour or minute");
        }

        int hour12 = hour24 % 12 == 0 ? 12 : hour24 % 12;
        int nextHour24 = (hour24 + 1) % 24;
        int nextHour12 = nextHour24 % 12 == 0 ? 12 : nextHour24 % 12;

        String hourWord = NumberWordMapper.toHourWord(hour12);
        String nextHourWord = NumberWordMapper.toHourWord(nextHour12);

        if (hour24 == 0 && minute == 0) {
            return new SpokenTimeResponse("midnight");
        }

        if (hour24 == 12 && minute == 0) {
            return new SpokenTimeResponse("noon");
        }

        if (minute == 0) {
            return new SpokenTimeResponse(hourWord + " o'clock");
        }

        if (minute == 15) {
            return new SpokenTimeResponse("quarter past " + hourWord);
        }

        if (minute == 30) {
            return new SpokenTimeResponse("half past " + hourWord);
        }

        if (minute == 45) {
            return new SpokenTimeResponse("quarter to " + nextHourWord);
        }

        if (minute < 30) {
            if (idiomaticPast.contains(minute)) {
                return new SpokenTimeResponse(NumberWordMapper.toMinuteWord(minute) + " past " + hourWord);
            } else {
                return new SpokenTimeResponse(hourWord + " " + NumberWordMapper.toMinuteWord(minute));
            }
        }

        // minute > 30
        if (idiomaticTo.contains(minute)) {
            int minsTo = 60 - minute;
            return new SpokenTimeResponse(NumberWordMapper.toMinuteWord(minsTo) + " to " + nextHourWord);
        } else {
            return new SpokenTimeResponse(hourWord + " " + NumberWordMapper.toMinuteWord(minute));
        }
    }
}