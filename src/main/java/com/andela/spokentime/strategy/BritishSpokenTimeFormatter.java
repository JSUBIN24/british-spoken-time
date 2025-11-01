package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.dto.TimeContext;
import com.andela.spokentime.util.NumberWordMapperUtil;
import com.andela.spokentime.util.TimeUtils;
import com.andela.spokentime.validator.TimeValidator;

import java.util.List;

public class BritishSpokenTimeFormatter implements SpokenTimeFormatter {

    private static final int HOURS_IN_DAY = 24;

    private final List<TimeFormatStrategy> strategies;


    public BritishSpokenTimeFormatter() {
        strategies = List.of(
                new MidnightNoonStrategy(),
                new SpecialCaseStrategy(),
                new IdiomaticPastStrategy(),
                new IdiomaticToStrategy(),
                new GenericTimeStrategy()
        );
    }

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

        for (TimeFormatStrategy strategy : strategies) {

            if (strategy.supports(ctx)) {
                return new SpokenTimeResponse(strategy.format(ctx));
            }
        }
        throw new IllegalStateException("No formatting  strategy found for minute " + minute);
    }
}