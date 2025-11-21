package com.test.inditex.application.usecase;

import com.test.inditex.application.port.input.PriceInputPort;
import com.test.inditex.domain.Price;
import com.test.inditex.domain.exception.PriceNotFoundException;
import com.test.inditex.domain.repository.PriceRepository;
import com.test.inditex.domain.valueobject.BrandId;
import com.test.inditex.domain.valueobject.ProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceUseCase implements PriceInputPort {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceUseCase(@Qualifier("h2PriceRepository") PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getEffectivePrice(LocalDateTime effectiveDate, ProductId productId, BrandId brandId) {
        return priceRepository.findEffectivePrice(effectiveDate, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("No price found for product %s, brand %s on date %s",
                                productId.value(),
                                brandId.value(),
                                effectiveDate)));
    }
}
