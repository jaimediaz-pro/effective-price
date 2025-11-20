package com.test.inditex.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Value Object representing a Product identifier.
 * Immutable and contains validation logic.
 */
@Getter
@EqualsAndHashCode
@ToString
public final class ProductId {

    private final Long value;

    private ProductId(Long value) {
        validate(value);
        this.value = value;
    }

    public static ProductId of(Long value) {
        return new ProductId(value);
    }

    private void validate(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("ProductId must be positive");
        }
    }
}
