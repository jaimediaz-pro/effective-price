package com.test.inditex.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void shouldCreateMoneyWithDoubleAmount() {
        Money money = Money.of(35.50, "EUR");

        assertEquals(new BigDecimal("35.50"), money.amount());
        assertEquals("EUR", money.currency());
    }

    @Test
    void shouldCreateMoneyWithBigDecimalAmount() {
        Money money = Money.of(new BigDecimal("100.99"), "USD");

        assertEquals(new BigDecimal("100.99"), money.amount());
        assertEquals("USD", money.currency());
    }

    @Test
    void shouldConvertCurrencyToUppercase() {
        Money money = Money.of(10.0, "eur");

        assertEquals("EUR", money.currency());
    }

    @Test
    void shouldRoundAmountToTwoDecimals() {
        Money money = Money.of(10.999, "EUR");

        assertEquals(new BigDecimal("11.00"), money.amount());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Money.of((BigDecimal) null, "EUR");
        });

        assertEquals("Amount cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Money.of(-10.0, "EUR");
        });

        assertEquals("Amount cannot be negative", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Money.of(10.0, null);
        });

        assertEquals("Currency cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Money.of(10.0, "");
        });

        assertEquals("Currency cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyIsNotThreeLetters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Money.of(10.0, "EU");
        });

        assertEquals("Currency must be a 3-letter ISO code", exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenSameCurrency() {
        Money money1 = Money.of(10.0, "EUR");
        Money money2 = Money.of(20.0, "EUR");

        assertTrue(money1.isSameCurrency(money2));
    }

    @Test
    void shouldReturnFalseWhenDifferentCurrency() {
        Money money1 = Money.of(10.0, "EUR");
        Money money2 = Money.of(10.0, "USD");

        assertFalse(money1.isSameCurrency(money2));
    }

    @Test
    void shouldBeEqualWhenSameAmountAndCurrency() {
        Money money1 = Money.of(35.50, "EUR");
        Money money2 = Money.of(35.50, "EUR");

        assertEquals(money1, money2);
        assertEquals(money1.hashCode(), money2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentAmount() {
        Money money1 = Money.of(35.50, "EUR");
        Money money2 = Money.of(40.00, "EUR");

        assertNotEquals(money1, money2);
    }

    @Test
    void shouldNotBeEqualWhenDifferentCurrency() {
        Money money1 = Money.of(35.50, "EUR");
        Money money2 = Money.of(35.50, "USD");

        assertNotEquals(money1, money2);
    }

    @Test
    void shouldHaveCorrectToString() {
        Money money = Money.of(35.50, "EUR");

        assertTrue(money.toString().contains("35.50"));
        assertTrue(money.toString().contains("EUR"));
    }
}
