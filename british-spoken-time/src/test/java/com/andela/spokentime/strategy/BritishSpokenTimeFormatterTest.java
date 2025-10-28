package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.exception.InvalidTimeFormatException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BritishSpokenTimeFormatterTest {

    private final BritishSpokenTimeFormatter formatter = new BritishSpokenTimeFormatter();

    @ParameterizedTest
    @CsvSource({
            "0000, midnight",
            "1200, noon",
            "0100, one o'clock",
            "1200, noon",
            "2300, eleven o'clock"
    })
    public void testValidTimes(String input, String expected) {
        SpokenTimeResponse response = formatter.format(input);
        assertEquals(expected, response.spokenTime());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2400", "1260", "abcd", "123", "9999", ""})
    public void testInvalidTimes(String input) {
        assertThrows(InvalidTimeFormatException.class, () -> formatter.format(input));
    }


}