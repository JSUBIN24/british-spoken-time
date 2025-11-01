package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.TimeContext;
import com.andela.spokentime.util.NumberWordMapperUtil;

import java.util.Set;

public class IdiomaticPastStrategy implements TimeFormatStrategy {

    private static final Set<Integer> IDIOMATIC_PAST = Set.of(5, 10, 15, 20, 25);

    @Override
    public boolean supports(TimeContext timeContext) {
        return timeContext.minute() < 30 && IDIOMATIC_PAST.contains(timeContext.minute());
    }

    @Override
    public String format(TimeContext timeContext) {
        return NumberWordMapperUtil.toMinuteWord(timeContext.minute()) + " past " + timeContext.hourWord();

    }
}
