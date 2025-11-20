package com.test.inditex.application.usecase;

import com.test.inditex.domain.Price;
import com.test.inditex.domain.exception.PriceNotFoundException;
import com.test.inditex.domain.repository.PriceRepository;
import com.test.inditex.domain.valueobject.BrandId;
import com.test.inditex.domain.valueobject.DateRange;
import com.test.inditex.domain.valueobject.Money;
import com.test.inditex.domain.valueobject.ProductId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceUseCaseTest {

    @InjectMocks
    PriceUseCase priceUseCase;

    @Mock
    PriceRepository priceRepository;

    @Test
    void getEffectivePrice() {
        // Create test data using Value Objects
        BrandId brandId = BrandId.of(1);
        ProductId productId = ProductId.of(35455L);
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        DateRange validityPeriod = DateRange.of(startDate, endDate);
        Money money = Money.of(35.50, "EUR");

        Price price = Price.builder()
                .brandId(brandId)
                .productId(productId)
                .validityPeriod(validityPeriod)
                .priceList(1L)
                .priority(0)
                .money(money)
                .build();

        // Mock repository behavior
        LocalDateTime effectiveDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        when(priceRepository.findEffectivePrice(any(LocalDateTime.class), any(ProductId.class), any(BrandId.class)))
                .thenReturn(Optional.of(price));

        // Test
        Price result = priceUseCase.getEffectivePrice(effectiveDate, productId, brandId);
        assertEquals(price, result);
    }

    @Test
    void getEffectivePrice_NotFound() {
        // Mock repository to return empty
        when(priceRepository.findEffectivePrice(any(LocalDateTime.class), any(ProductId.class), any(BrandId.class)))
                .thenReturn(Optional.empty());

        // Test that exception is thrown
        BrandId brandId = BrandId.of(1);
        ProductId productId = ProductId.of(35455L);
        LocalDateTime effectiveDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        assertThrows(PriceNotFoundException.class,
                () -> priceUseCase.getEffectivePrice(effectiveDate, productId, brandId));
    }
}