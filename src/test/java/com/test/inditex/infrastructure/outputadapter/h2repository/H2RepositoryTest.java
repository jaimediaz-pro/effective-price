package com.test.inditex.infrastructure.outputadapter.h2repository;

import com.test.inditex.domain.Price;
import com.test.inditex.domain.valueobject.BrandId;
import com.test.inditex.domain.valueobject.DateRange;
import com.test.inditex.domain.valueobject.Money;
import com.test.inditex.domain.valueobject.ProductId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class H2RepositoryTest {

    @InjectMocks
    H2PriceRepository h2PriceRepository;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    void findEffectivePrice_Found() {
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

        List<Price> priceList = new ArrayList<>();
        priceList.add(price);

        // Mock jdbcTemplate to return the price
        Mockito.when(jdbcTemplate.query(anyString(), any(RowMapper.class), any(), any(), any(), any()))
                .thenReturn(priceList);

        // Test
        LocalDateTime effectiveDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Optional<Price> result = h2PriceRepository.findEffectivePrice(effectiveDate, productId, brandId);

        assertTrue(result.isPresent());
        assertEquals(price, result.get());
    }

    @Test
    void findEffectivePrice_NotFound() {
        // Mock jdbcTemplate to return empty list
        List<Price> priceList = new ArrayList<>();
        Mockito.when(jdbcTemplate.query(anyString(), any(RowMapper.class), any(), any(), any(), any()))
                .thenReturn(priceList);

        // Test
        BrandId brandId = BrandId.of(1);
        ProductId productId = ProductId.of(35455L);
        LocalDateTime effectiveDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        Optional<Price> result = h2PriceRepository.findEffectivePrice(effectiveDate, productId, brandId);

        assertTrue(result.isEmpty());
    }
}