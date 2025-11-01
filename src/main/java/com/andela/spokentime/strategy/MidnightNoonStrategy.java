package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.TimeContext;

public class MidnightNoonStrategy implements TimeFormatStrategy {
    @Override
    public boolean supports(TimeContext timeContext) {
        return timeContext.hour24() == 0 && timeContext.minute() == 0 || timeContext.hour24() == 12 && timeContext.minute() == 0;
    }

    @Override
    public String format(TimeContext ctx) {
        if (ctx.hour24() == 0) return "midnight";
        else return "noon";
    }
}
