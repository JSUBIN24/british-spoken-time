package com.andela.spokentime.service;

import com.andela.spokentime.dto.SpokenTimeResponse;
import com.andela.spokentime.exception.InvalidTimeFormatException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpokenTimeServiceIntegrationTest {

    @Autowired
    private SpokenTimeService spokenTimeService;

    @Test
    void testMidnight() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("00:00", "british");
        assertEquals("midnight", response.spokenTime());
    }

    @Test
    void testNoon() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("12:00", "british");
        assertEquals("noon", response.spokenTime());
    }

    @Test
    void testQuarterPast() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("03:15", "british");
        assertEquals("quarter past three", response.spokenTime());
    }

    @Test
    void testHalfPast() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("12:30", "british");
        assertEquals("half past twelve", response.spokenTime());
    }

    @Test
    void testQuarterTo() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("03:45", "british");
        assertEquals("quarter to four", response.spokenTime());
    }

    @Test
    void testExactMinute() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("03:31", "british");
        assertEquals("three thirty one", response.spokenTime());
    }

    @Test
    void testFivePast() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("05:05", "british");
        assertEquals("five past five", response.spokenTime());
    }

    @Test
    void testTwentyFiveTo() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("05:35", "british");
        assertEquals("twenty five to six", response.spokenTime());
    }

    @Test
    void testInvalidInput() {
        assertThrows(InvalidTimeFormatException.class, () -> {
            spokenTimeService.getSpokenTime("99:99", "british");
        });
    }
}