package com.test.inditex.infrastructure.inputport;

import com.test.inditex.domain.Price;

import java.sql.Timestamp;

public interface PriceInputPort {

    public Price getEffectivePrice(Timestamp effectiveDate, Long productId, int brandId);
}
