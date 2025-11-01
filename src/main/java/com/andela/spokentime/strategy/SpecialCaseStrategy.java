package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.TimeContext;

import java.util.Map;
import java.util.function.Function;

public class SpecialCaseStrategy implements TimeFormatStrategy {

    private static final Map<Integer, Function<TimeContext, String>> SPECIAL_CASES = Map.of(
            0, ctx -> ctx.hourWord() + " o'clock",
            15, ctx -> "quarter past " + ctx.hourWord(),
            30, ctx -> "half past " + ctx.hourWord(),
            45, ctx -> "quarter to " + ctx.nextHourWord()
    );

    @Override
    public boolean supports(TimeContext timeContext) {
        return SPECIAL_CASES.containsKey(timeContext.minute());
    }

    @Override
    public String format(TimeContext timeContext) {
        return SPECIAL_CASES.get(timeContext.minute()).apply(timeContext);
    }
}
