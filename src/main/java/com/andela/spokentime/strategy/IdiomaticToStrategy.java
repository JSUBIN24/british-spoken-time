package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.TimeContext;
import com.andela.spokentime.util.NumberWordMapperUtil;

import java.util.Set;

public class IdiomaticToStrategy implements TimeFormatStrategy {
    private static final Set<Integer> IDIOMATIC_TO = Set.of(35, 40, 45, 50, 55);
    private static final int MINUTES_IN_HOUR = 60;

    @Override
    public boolean supports(TimeContext ctx) {
        return ctx.minute() > 30 && IDIOMATIC_TO.contains(ctx.minute());
    }

    @Override
    public String format(TimeContext ctx) {
        int minsTo = MINUTES_IN_HOUR - ctx.minute();
        return NumberWordMapperUtil.toMinuteWord(minsTo) + " to " + ctx.nextHourWord();
    }
}
