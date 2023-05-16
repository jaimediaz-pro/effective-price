package com.test.inditex.application.usecase;

import com.test.inditex.domain.Price;
import com.test.inditex.infrastructure.inputport.PriceInputPort;
import com.test.inditex.infrastructure.outputport.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class PriceUseCase implements PriceInputPort {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceUseCase(@Qualifier("h2repository") PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getEffectivePrice(Timestamp effectiveDate, Long productId, int brandId) {
        return priceRepository.getEffectivePrice(effectiveDate, productId, brandId);
    }
}
