package com.test.inditex.domain.valueobject;

/**
 * Value Object representing a Product identifier.
 * Immutable and contains validation logic.
 */
public record ProductId(Long value) {

    public ProductId {
        if (value == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("ProductId must be positive");
        }
    }

    public static ProductId of(Long value) {
        return new ProductId(value);
    }
}
