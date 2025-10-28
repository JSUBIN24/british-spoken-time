package com.andela.spokentime.factory;

import com.andela.spokentime.strategy.BritishSpokenTimeFormatter;
import com.andela.spokentime.strategy.SpokenTimeFormatter;

public class SpokenTimeFormatterFactory {
    public static SpokenTimeFormatter getFormatter(String locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Locale cannot be null");
        }
        return switch (locale.toLowerCase()) {
            case "british" -> new BritishSpokenTimeFormatter();
            default -> throw new IllegalArgumentException("Unsupported locale: " + locale);
        };
    }
}
