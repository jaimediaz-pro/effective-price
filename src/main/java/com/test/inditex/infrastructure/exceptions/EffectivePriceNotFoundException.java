package com.test.inditex.infrastructure.exceptions;

public class EffectivePriceNotFoundException  extends RuntimeException {
    public EffectivePriceNotFoundException(String s) {
        super("Effective price not found in database");
    }
}
