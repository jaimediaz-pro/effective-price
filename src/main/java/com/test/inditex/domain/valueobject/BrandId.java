package com.test.inditex.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Value Object representing a Brand identifier.
 * Immutable and contains validation logic.
 */
@Getter
@EqualsAndHashCode
@ToString
public final class BrandId {

    private final Integer value;

    private BrandId(Integer value) {
        validate(value);
        this.value = value;
    }

    public static BrandId of(Integer value) {
        return new BrandId(value);
    }

    private void validate(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("BrandId cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("BrandId must be positive");
        }
    }
}
