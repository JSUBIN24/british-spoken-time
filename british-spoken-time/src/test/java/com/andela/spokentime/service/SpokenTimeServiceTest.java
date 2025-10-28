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
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("0000", "british");
        assertEquals("midnight", response.spokenTime());
    }

    @Test
    void testNoon() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("1200", "british");
        assertEquals("noon", response.spokenTime());
    }

    @Test
    void testQuarterPast() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("0315", "british");
        assertEquals("quarter past three", response.spokenTime());
    }

    @Test
    void testHalfPast() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("1230", "british");
        assertEquals("half past twelve", response.spokenTime());
    }

    @Test
    void testQuarterTo() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("0345", "british");
        assertEquals("quarter to four", response.spokenTime());
    }

    @Test
    void testExactMinute() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("0331", "british");
        assertEquals("three thirty one", response.spokenTime());
    }

    @Test
    void testFivePast() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("0505", "british");
        assertEquals("five past five", response.spokenTime());
    }

    @Test
    void testTwentyFiveTo() {
        SpokenTimeResponse response = spokenTimeService.getSpokenTime("0535", "british");
        assertEquals("twenty five to six", response.spokenTime());
    }

    @Test
    void testInvalidInput() {
        assertThrows(InvalidTimeFormatException.class, () -> {
            spokenTimeService.getSpokenTime("9999", "british");
        });
    }
}