package com.test.inditex.domain.repository;

import com.test.inditex.domain.Price;
import com.test.inditex.domain.valueobject.BrandId;
import com.test.inditex.domain.valueobject.ProductId;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Domain repository port for Price.
 * This interface belongs to the domain and will be implemented by
 * infrastructure adapters.
 */
public interface PriceRepository {

    /**
     * Finds the effective price for the given criteria.
     * Returns the price with the highest priority if multiple matches exist.
     * 
     * @param effectiveDate the date to check
     * @param productId     the product identifier
     * @param brandId       the brand identifier
     * @return Optional containing the price if found, empty otherwise
     */
    Optional<Price> findEffectivePrice(LocalDateTime effectiveDate, ProductId productId, BrandId brandId);
}
