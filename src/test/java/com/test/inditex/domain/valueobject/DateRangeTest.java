package com.test.inditex.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateRangeTest {

    @Test
    void shouldCreateDateRangeWithValidDates() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);

        DateRange dateRange = DateRange.of(start, end);

        assertEquals(start, dateRange.getStartDate());
        assertEquals(end, dateRange.getEndDate());
    }

    @Test
    void shouldThrowExceptionWhenStartDateIsNull() {
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DateRange.of(null, end);
        });

        assertEquals("Start date cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEndDateIsNull() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DateRange.of(start, null);
        });

        assertEquals("End date cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
        LocalDateTime start = LocalDateTime.of(2020, 12, 31, 23, 59);
        LocalDateTime end = LocalDateTime.of(2020, 6, 14, 0, 0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DateRange.of(start, end);
        });

        assertEquals("Start date must be before end date", exception.getMessage());
    }

    @Test
    void shouldAllowSameStartAndEndDate() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 12, 0);

        DateRange dateRange = DateRange.of(date, date);

        assertEquals(date, dateRange.getStartDate());
        assertEquals(date, dateRange.getEndDate());
    }

    @Test
    void shouldReturnTrueWhenDateIsWithinRange() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);
        LocalDateTime testDate = LocalDateTime.of(2020, 9, 15, 12, 0);

        DateRange dateRange = DateRange.of(start, end);

        assertTrue(dateRange.contains(testDate));
    }

    @Test
    void shouldReturnTrueWhenDateIsStartDate() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);

        DateRange dateRange = DateRange.of(start, end);

        assertTrue(dateRange.contains(start));
    }

    @Test
    void shouldReturnTrueWhenDateIsEndDate() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);

        DateRange dateRange = DateRange.of(start, end);

        assertTrue(dateRange.contains(end));
    }

    @Test
    void shouldReturnFalseWhenDateIsBeforeRange() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);
        LocalDateTime testDate = LocalDateTime.of(2020, 6, 13, 23, 59);

        DateRange dateRange = DateRange.of(start, end);

        assertFalse(dateRange.contains(testDate));
    }

    @Test
    void shouldReturnFalseWhenDateIsAfterRange() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);
        LocalDateTime testDate = LocalDateTime.of(2021, 1, 1, 0, 0);

        DateRange dateRange = DateRange.of(start, end);

        assertFalse(dateRange.contains(testDate));
    }

    @Test
    void shouldReturnFalseWhenDateIsNull() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);

        DateRange dateRange = DateRange.of(start, end);

        assertFalse(dateRange.contains(null));
    }

    @Test
    void shouldBeEqualWhenSameDates() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);

        DateRange dateRange1 = DateRange.of(start, end);
        DateRange dateRange2 = DateRange.of(start, end);

        assertEquals(dateRange1, dateRange2);
        assertEquals(dateRange1.hashCode(), dateRange2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentDates() {
        LocalDateTime start1 = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end1 = LocalDateTime.of(2020, 12, 31, 23, 59);
        LocalDateTime start2 = LocalDateTime.of(2020, 7, 1, 0, 0);
        LocalDateTime end2 = LocalDateTime.of(2020, 12, 31, 23, 59);

        DateRange dateRange1 = DateRange.of(start1, end1);
        DateRange dateRange2 = DateRange.of(start2, end2);

        assertNotEquals(dateRange1, dateRange2);
    }

    @Test
    void shouldHaveCorrectToString() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);

        DateRange dateRange = DateRange.of(start, end);
        String toString = dateRange.toString();

        assertTrue(toString.contains("2020-06-14"));
        assertTrue(toString.contains("2020-12-31"));
    }
}
