package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.dto.TimeContext;
import com.andela.spokentime.util.NumberWordMapperUtil;
import com.andela.spokentime.util.TimeUtils;
import com.andela.spokentime.validator.TimeValidator;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class BritishSpokenTimeFormatter implements SpokenTimeFormatter {

    private static final int MIDNIGHT_HOUR = 0;
    private static final int NOON_HOUR = 12;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final int HALF_HOUR = 30;
    private static final Set<Integer> IDIOMATIC_PAST = Set.of(5, 10, 15, 20, 25);
    private static final Set<Integer> IDIOMATIC_TO = Set.of(35, 40, 45, 50, 55);


    private static final Map<Integer, Function<TimeContext, String>> SPECIAL_CASES = Map.of(
            0, ctx -> ctx.hourWord() + " o'clock",
            15, ctx -> "quarter past " + ctx.hourWord(),
            30, ctx -> "half past " + ctx.hourWord(),
            45, ctx -> "quarter to " + ctx.nextHourWord()
    );

    @Override
    public SpokenTimeResponse format(String time) {
        TimeValidator.validate(time);

        String[] split = time.split(":");
        int hour24 = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);

        int hour12 = TimeUtils.to12Hour(hour24);
        int nextHour12 = TimeUtils.to12Hour((hour24 + 1) % HOURS_IN_DAY);

        String hourWord = NumberWordMapperUtil.toHourWord(hour12);
        String nextHourWord = NumberWordMapperUtil.toHourWord(nextHour12);
        TimeContext ctx = new TimeContext(hourWord, nextHourWord, hour24, minute);

        if (hour24 == MIDNIGHT_HOUR && minute == 0) return spoken("midnight");
        if (hour24 == NOON_HOUR && minute == 0) return spoken("noon");

        if (SPECIAL_CASES.containsKey(minute)) {
            return spoken(SPECIAL_CASES.get(minute).apply(ctx));
        }

        if (minute < HALF_HOUR) {
            if (IDIOMATIC_PAST.contains(minute)) {
                return spoken(NumberWordMapperUtil.toMinuteWord(minute) + " past " + hourWord);
            }
            return spoken(hourWord + " " + NumberWordMapperUtil.toMinuteWord(minute));
        }

        // minute > 30
        if (IDIOMATIC_TO.contains(minute)) {
            int minsTo = MINUTES_IN_HOUR - minute;
            return spoken(NumberWordMapperUtil.toMinuteWord(minsTo) + " to " + nextHourWord);
        }

        return spoken(hourWord + " " + NumberWordMapperUtil.toMinuteWord(minute));
    }

    private SpokenTimeResponse spoken(String phrase) {
        return new SpokenTimeResponse(phrase);
    }
}