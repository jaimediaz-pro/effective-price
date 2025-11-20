package com.test.inditex.infrastructure.inputadapter.http.mapper;

import com.test.inditex.domain.Price;
import com.test.inditex.domain.valueobject.BrandId;
import com.test.inditex.domain.valueobject.DateRange;
import com.test.inditex.domain.valueobject.Money;
import com.test.inditex.domain.valueobject.ProductId;
import com.test.inditex.infrastructure.inputadapter.http.dto.PriceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceMapperTest {

    private PriceMapper priceMapper;

    @BeforeEach
    void setUp() {
        priceMapper = new PriceMapper();
    }

    @Test
    void shouldMapPriceToDTOSuccessfully() {
        // Given
        BrandId brandId = BrandId.of(1);
        ProductId productId = ProductId.of(35455L);
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59);
        DateRange validityPeriod = DateRange.of(startDate, endDate);
        Money money = Money.of(35.50, "EUR");
        LocalDateTime effectiveDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        Price price = Price.builder()
                .brandId(brandId)
                .productId(productId)
                .validityPeriod(validityPeriod)
                .priceList(1L)
                .priority(0)
                .money(money)
                .build();

        // When
        PriceDTO dto = priceMapper.toDTO(price, effectiveDate);

        // Then
        assertNotNull(dto);
        assertEquals(35455L, dto.getProductId());
        assertEquals(1, dto.getBrandId());
        assertEquals(1L, dto.getPriceList());
        assertEquals(startDate, dto.getStartDate());
        assertEquals(endDate, dto.getEndDate());
        assertEquals(effectiveDate, dto.getEffectiveDate());
        assertEquals(35.50, dto.getPrice());
        assertEquals("EUR", dto.getCurrency());
    }

    @Test
    void shouldReturnNullWhenPriceIsNull() {
        LocalDateTime effectiveDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        PriceDTO dto = priceMapper.toDTO(null, effectiveDate);

        assertNull(dto);
    }

    @Test
    void shouldCorrectlyMapMoneyAmount() {
        Price price = Price.builder()
                .brandId(BrandId.of(1))
                .productId(ProductId.of(35455L))
                .validityPeriod(DateRange.of(
                        LocalDateTime.of(2020, 6, 14, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59)))
                .priceList(1L)
                .priority(0)
                .money(Money.of(99.99, "USD"))
                .build();

        LocalDateTime effectiveDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        PriceDTO dto = priceMapper.toDTO(price, effectiveDate);

        assertEquals(99.99, dto.getPrice());
        assertEquals("USD", dto.getCurrency());
    }

    @Test
    void shouldPreserveAllValueObjectData() {
        BrandId brandId = BrandId.of(2);
        ProductId productId = ProductId.of(12345L);

        Price price = Price.builder()
                .brandId(brandId)
                .productId(productId)
                .validityPeriod(DateRange.of(
                        LocalDateTime.of(2020, 6, 15, 16, 0),
                        LocalDateTime.of(2020, 6, 15, 23, 59)))
                .priceList(3L)
                .priority(5)
                .money(Money.of(25.45, "EUR"))
                .build();

        LocalDateTime effectiveDate = LocalDateTime.of(2020, 6, 15, 18, 0);

        PriceDTO dto = priceMapper.toDTO(price, effectiveDate);

        assertEquals(12345L, dto.getProductId());
        assertEquals(2, dto.getBrandId());
        assertEquals(3L, dto.getPriceList());
        assertEquals(25.45, dto.getPrice());
        assertEquals("EUR", dto.getCurrency());
        assertEquals(effectiveDate, dto.getEffectiveDate());
    }
}
