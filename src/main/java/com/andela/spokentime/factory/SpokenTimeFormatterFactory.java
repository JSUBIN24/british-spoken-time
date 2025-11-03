package com.andela.spokentime.factory;

import com.andela.spokentime.strategy.BritishSpokenTimeFormatterImplementation;
import com.andela.spokentime.strategy.SpokenTimeFormatter;

import java.util.Map;
import java.util.function.Supplier;

public class SpokenTimeFormatterFactory {

    private SpokenTimeFormatterFactory() {
    }

    public static final Map<String, Supplier<SpokenTimeFormatter>> FORMATTER = Map.of("british", BritishSpokenTimeFormatterImplementation::new);

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
