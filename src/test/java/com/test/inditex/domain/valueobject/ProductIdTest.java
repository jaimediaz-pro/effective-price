package com.test.inditex.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {

    @Test
    void shouldCreateProductIdWithValidValue() {
        ProductId productId = ProductId.of(35455L);

        assertEquals(35455L, productId.value());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductId.of(null);
        });

        assertEquals("ProductId cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenValueIsZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductId.of(0L);
        });

        assertEquals("ProductId must be positive", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductId.of(-1L);
        });

        assertEquals("ProductId must be positive", exception.getMessage());
    }

    @Test
    void shouldBeEqualWhenSameValue() {
        ProductId productId1 = ProductId.of(35455L);
        ProductId productId2 = ProductId.of(35455L);

        assertEquals(productId1, productId2);
        assertEquals(productId1.hashCode(), productId2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValue() {
        ProductId productId1 = ProductId.of(35455L);
        ProductId productId2 = ProductId.of(12345L);

        assertNotEquals(productId1, productId2);
    }

    @Test
    void shouldHaveCorrectToString() {
        ProductId productId = ProductId.of(35455L);

        assertTrue(productId.toString().contains("35455"));
    }
}
