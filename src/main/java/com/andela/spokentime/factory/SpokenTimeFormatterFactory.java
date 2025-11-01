package com.andela.spokentime.factory;

import com.andela.spokentime.strategy.BritishSpokenTimeFormatter;
import com.andela.spokentime.strategy.SpokenTimeFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SpokenTimeFormatterFactory {


    public static final Map<String, Supplier<SpokenTimeFormatter>> FORMATTER = Map.of("british", BritishSpokenTimeFormatter::new);

    public static SpokenTimeFormatter getFormatter(String locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Locale cannot be null");
        }

        var formatterSupplier = FORMATTER.get(locale.toLowerCase());
        if (formatterSupplier == null) {
            throw new IllegalArgumentException("Unsupported locale: " + locale);
        }
        return formatterSupplier.get();

    }
}
