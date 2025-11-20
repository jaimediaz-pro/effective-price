package com.test.inditex.domain.exception;

/**
 * Domain exception thrown when a price is not found for the given criteria.
 * This represents a business rule violation.
 */
public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(String message) {
        super(message);
    }

    public PriceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
