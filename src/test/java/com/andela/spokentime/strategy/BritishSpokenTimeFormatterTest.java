package com.andela.spokentime.strategy;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.exception.InvalidTimeFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BritishSpokenTimeFormatterTest {

    private BritishSpokenTimeFormatter formatter;

    @BeforeEach
    void setUp() {
        formatter = new BritishSpokenTimeFormatter();
    }

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


    @Test
    void testIdiomaticPast() {
        assertEquals("five past three", formatter.format("0305").spokenTime());
        assertEquals("ten past three", formatter.format("0310").spokenTime());
        assertEquals("quarter past three", formatter.format("0315").spokenTime());
        assertEquals("twenty past three", formatter.format("0320").spokenTime());
        assertEquals("twenty five past three", formatter.format("0325").spokenTime());
    }

    @Test
    void testIdiomaticTo() {
        assertEquals("twenty five to four", formatter.format("0335").spokenTime());
        assertEquals("twenty to four", formatter.format("0340").spokenTime());
        assertEquals("quarter to four", formatter.format("0345").spokenTime());
        assertEquals("ten to four", formatter.format("0350").spokenTime());
        assertEquals("five to four", formatter.format("0355").spokenTime());
    }

    @Test
    void testInvalidInputs() {
        assertThrows(InvalidTimeFormatException.class, () -> formatter.format(null));
        assertThrows(InvalidTimeFormatException.class, () -> formatter.format("012"));
        assertThrows(InvalidTimeFormatException.class, () -> formatter.format("abcd"));
        assertThrows(InvalidTimeFormatException.class, () -> formatter.format("2560"));
        assertThrows(InvalidTimeFormatException.class, () -> formatter.format("1260"));
    }

}