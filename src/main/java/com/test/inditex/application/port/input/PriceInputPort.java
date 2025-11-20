package com.test.inditex.application.port.input;

import com.test.inditex.domain.Price;
import com.test.inditex.domain.valueobject.BrandId;
import com.test.inditex.domain.valueobject.ProductId;

import java.time.LocalDateTime;

/**
 * Input port for price-related use cases.
 * This interface defines the application's API.
 */
public interface PriceInputPort {

    /**
     * Retrieves the effective price for the given criteria.
     * 
     * @param effectiveDate the date to check
     * @param productId     the product identifier
     * @param brandId       the brand identifier
     * @return the effective price
     * @throws com.test.inditex.domain.exception.PriceNotFoundException if no price
     *                                                                  is found
     */
    Price getEffectivePrice(LocalDateTime effectiveDate, ProductId productId, BrandId brandId);
}
