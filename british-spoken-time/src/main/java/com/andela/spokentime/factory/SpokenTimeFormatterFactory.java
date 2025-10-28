package com.andela.spokentime.factory;

import com.andela.spokentime.strategy.BritishSpokenTimeFormatter;
import com.andela.spokentime.strategy.SpokenTimeFormatter;

public class SpokenTimeFormatterFactory {
    public static SpokenTimeFormatter getFormatter(String locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Locale cannot be null");
        }
        switch (locale.toLowerCase()) {
            case "british":
                return new BritishSpokenTimeFormatter();
            default:
                throw new IllegalArgumentException("Unsupported locale: " + locale);
        }
    }
}
