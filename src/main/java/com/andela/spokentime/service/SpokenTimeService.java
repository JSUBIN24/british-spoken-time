package com.andela.spokentime.service;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.factory.SpokenTimeFormatterFactory;
import com.andela.spokentime.strategy.SpokenTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class SpokenTimeService {
    public SpokenTimeResponse getSpokenTime(String time, String locale) {
        SpokenTimeFormatter formatter = SpokenTimeFormatterFactory.getFormatter(locale);
        return formatter.format(time);
    }
}
