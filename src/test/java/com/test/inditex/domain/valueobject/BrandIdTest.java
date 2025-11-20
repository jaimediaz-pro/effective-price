package com.test.inditex.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandIdTest {

    @Test
    void shouldCreateBrandIdWithValidValue() {
        BrandId brandId = BrandId.of(1);

        assertEquals(1, brandId.getValue());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            BrandId.of(null);
        });

        assertEquals("BrandId cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenValueIsZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            BrandId.of(0);
        });

        assertEquals("BrandId must be positive", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            BrandId.of(-1);
        });

        assertEquals("BrandId must be positive", exception.getMessage());
    }

    @Test
    void shouldBeEqualWhenSameValue() {
        BrandId brandId1 = BrandId.of(1);
        BrandId brandId2 = BrandId.of(1);

        assertEquals(brandId1, brandId2);
        assertEquals(brandId1.hashCode(), brandId2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValue() {
        BrandId brandId1 = BrandId.of(1);
        BrandId brandId2 = BrandId.of(2);

        assertNotEquals(brandId1, brandId2);
    }

    @Test
    void shouldHaveCorrectToString() {
        BrandId brandId = BrandId.of(1);

        assertTrue(brandId.toString().contains("1"));
    }
}
