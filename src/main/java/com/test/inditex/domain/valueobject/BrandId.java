package com.test.inditex.domain.valueobject;

/**
 * Value Object representing a Brand identifier.
 * Immutable and contains validation logic.
 */
public record BrandId(Integer value) {

    public BrandId {
        if (value == null) {
            throw new IllegalArgumentException("BrandId cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("BrandId must be positive");
        }
    }

    public static BrandId of(Integer value) {
        return new BrandId(value);
    }
}
