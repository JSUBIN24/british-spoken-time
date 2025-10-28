package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.exception.InvalidTimeFormatException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BritishSpokenTimeFormatterTest {

    private final BritishSpokenTimeFormatter formatter = new BritishSpokenTimeFormatter();

    @ParameterizedTest
    @CsvFileSource(resources = "/british_spoken_time_full.csv")
    public void testValidTimes(String input, String expected) {
        SpokenTimeResponse response = formatter.format(input);
        assertEquals(expected, response.spokenTime());
    }
    @ParameterizedTest
    @CsvSource({
            "0000, midnight",
            "0100, one o'clock",
            "0205, five past two",
            "0310, ten past three",
            "0415, quarter past four",
            "0520, twenty past five",
            "0625, twenty five past six",
            "0632, six thirty two",
            "0730, half past seven",
            "0735, twenty five to eight",
            "0840, twenty to nine",
            "0945, quarter to ten",
            "1050, ten to eleven",
            "1155, five to twelve",
            "1200, noon",
            "2300, eleven o'clock"
    })
    public void testValidTimesInput(String input, String expected) {
        SpokenTimeResponse response = formatter.format(input);
        assertEquals(expected, response.spokenTime());
    }


    @ParameterizedTest
    @ValueSource(strings = {"2400", "1260", "abcd", "123", "9999", ""})
    public void testInvalidTimes(String input) {
        assertThrows(InvalidTimeFormatException.class, () -> formatter.format(input));
    }


}