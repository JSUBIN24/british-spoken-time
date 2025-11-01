package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.TimeContext;
import com.andela.spokentime.util.NumberWordMapperUtil;

public class GenericTimeStrategy implements TimeFormatStrategy {
    @Override
    public boolean supports(TimeContext ctx) {
        return true;
    }

    @Override
    public String format(TimeContext ctx) {
        return ctx.hourWord() + " " + NumberWordMapperUtil.toMinuteWord(ctx.minute());
    }
}
