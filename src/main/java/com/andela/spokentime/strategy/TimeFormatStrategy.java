package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.TimeContext;

public interface TimeFormatStrategy {

    boolean supports(TimeContext timeContext);

    String format(TimeContext timeContext);
}
