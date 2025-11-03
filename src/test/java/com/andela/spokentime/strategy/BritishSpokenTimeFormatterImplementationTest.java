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

class BritishSpokenTimeFormatterImplementationTest {

    private BritishSpokenTimeFormatterImplementation formatter;

    @BeforeEach
    void setUp() {
        formatter = new BritishSpokenTimeFormatterImplementation();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/british_spoken_time_full.csv")
    void testValidTimes(String input, String expected) {
        SpokenTimeResponse response = formatter.format(input);
        assertEquals(expected, response.spokenTime());
    }
    @ParameterizedTest
    @CsvSource({
            "00:00, midnight",
            "01:00, one o'clock",
            "02:05, five past two",
            "03:10, ten past three",
            "04:15, quarter past four",
            "05:20, twenty past five",
            "06:25, twenty five past six",
            "06:32, six thirty two",
            "07:30, half past seven",
            "07:35, twenty five to eight",
            "08:40, twenty to nine",
            "09:45, quarter to ten",
            "10:50, ten to eleven",
            "11:55, five to twelve",
            "12:00, noon",
            "23:00, eleven o'clock"
    })
    void testValidTimesInput(String input, String expected) {
        testValidTimes(input,expected);
    }


    @ParameterizedTest
    @ValueSource(strings = {"24:00", "12:60", "abcd", "123", "9999", "24:0000", "000:441", "-14:-24"})
    void testInvalidTimes(String input) {
        assertThrows(InvalidTimeFormatException.class, () -> formatter.format(input));
    }


    @Test
    void testIdiomaticPast() {
        assertEquals("five past three", formatter.format("03:05").spokenTime());
        assertEquals("ten past three", formatter.format("03:10").spokenTime());
        assertEquals("quarter past three", formatter.format("03:15").spokenTime());
        assertEquals("twenty past three", formatter.format("03:20").spokenTime());
        assertEquals("twenty five past three", formatter.format("03:25").spokenTime());
    }

    @Test
    void testIdiomaticTo() {
        assertEquals("twenty five to four", formatter.format("03:35").spokenTime());
        assertEquals("twenty to four", formatter.format("03:40").spokenTime());
        assertEquals("quarter to four", formatter.format("03:45").spokenTime());
        assertEquals("ten to four", formatter.format("03:50").spokenTime());
        assertEquals("five to four", formatter.format("03:55").spokenTime());
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