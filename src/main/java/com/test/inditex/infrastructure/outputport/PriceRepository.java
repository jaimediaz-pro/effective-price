package com.test.inditex.infrastructure.outputport;

import com.test.inditex.domain.Price;

import java.sql.Timestamp;

public interface PriceRepository {

    public Price getEffectivePrice(Timestamp effectiveDate, Long productId, int brandId);

}