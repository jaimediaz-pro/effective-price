package com.test.inditex.domain;

import com.test.inditex.domain.valueobject.BrandId;
import com.test.inditex.domain.valueobject.DateRange;
import com.test.inditex.domain.valueobject.Money;
import com.test.inditex.domain.valueobject.ProductId;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void shouldBuildPriceWithValidData() {
        BrandId brandId = BrandId.of(1);
        ProductId productId = ProductId.of(35455L);
        DateRange validityPeriod = DateRange.of(
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59));
        Money money = Money.of(35.50, "EUR");

        Price price = Price.builder()
                .brandId(brandId)
                .productId(productId)
                .validityPeriod(validityPeriod)
                .priceList(1L)
                .priority(0)
                .money(money)
                .build();

        assertNotNull(price);
        assertEquals(brandId, price.getBrandId());
        assertEquals(productId, price.getProductId());
        assertEquals(validityPeriod, price.getValidityPeriod());
        assertEquals(1L, price.getPriceList());
        assertEquals(0, price.getPriority());
        assertEquals(money, price.getMoney());
    }

    @Test
    void shouldThrowExceptionWhenBrandIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Price.builder()
                    .brandId(null)
                    .productId(ProductId.of(35455L))
                    .validityPeriod(DateRange.of(
                            LocalDateTime.of(2020, 6, 14, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59)))
                    .priceList(1L)
                    .priority(0)
                    .money(Money.of(35.50, "EUR"))
                    .build();
        });

        assertEquals("BrandId cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProductIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Price.builder()
                    .brandId(BrandId.of(1))
                    .productId(null)
                    .validityPeriod(DateRange.of(
                            LocalDateTime.of(2020, 6, 14, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59)))
                    .priceList(1L)
                    .priority(0)
                    .money(Money.of(35.50, "EUR"))
                    .build();
        });

        assertEquals("ProductId cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenValidityPeriodIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Price.builder()
                    .brandId(BrandId.of(1))
                    .productId(ProductId.of(35455L))
                    .validityPeriod(null)
                    .priceList(1L)
                    .priority(0)
                    .money(Money.of(35.50, "EUR"))
                    .build();
        });

        assertEquals("Validity period cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPriceListIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Price.builder()
                    .brandId(BrandId.of(1))
                    .productId(ProductId.of(35455L))
                    .validityPeriod(DateRange.of(
                            LocalDateTime.of(2020, 6, 14, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59)))
                    .priceList(null)
                    .priority(0)
                    .money(Money.of(35.50, "EUR"))
                    .build();
        });

        assertEquals("Price list must be positive", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPriceListIsNotPositive() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Price.builder()
                    .brandId(BrandId.of(1))
                    .productId(ProductId.of(35455L))
                    .validityPeriod(DateRange.of(
                            LocalDateTime.of(2020, 6, 14, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59)))
                    .priceList(0L)
                    .priority(0)
                    .money(Money.of(35.50, "EUR"))
                    .build();
        });

        assertEquals("Price list must be positive", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPriorityIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Price.builder()
                    .brandId(BrandId.of(1))
                    .productId(ProductId.of(35455L))
                    .validityPeriod(DateRange.of(
                            LocalDateTime.of(2020, 6, 14, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59)))
                    .priceList(1L)
                    .priority(null)
                    .money(Money.of(35.50, "EUR"))
                    .build();
        });

        assertEquals("Priority cannot be null or negative", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPriorityIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Price.builder()
                    .brandId(BrandId.of(1))
                    .productId(ProductId.of(35455L))
                    .validityPeriod(DateRange.of(
                            LocalDateTime.of(2020, 6, 14, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59)))
                    .priceList(1L)
                    .priority(-1)
                    .money(Money.of(35.50, "EUR"))
                    .build();
        });

        assertEquals("Priority cannot be null or negative", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMoneyIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Price.builder()
                    .brandId(BrandId.of(1))
                    .productId(ProductId.of(35455L))
                    .validityPeriod(DateRange.of(
                            LocalDateTime.of(2020, 6, 14, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59)))
                    .priceList(1L)
                    .priority(0)
                    .money(null)
                    .build();
        });

        assertEquals("Money cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenDateIsApplicable() {
        Price price = Price.builder()
                .brandId(BrandId.of(1))
                .productId(ProductId.of(35455L))
                .validityPeriod(DateRange.of(
                        LocalDateTime.of(2020, 6, 14, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59)))
                .priceList(1L)
                .priority(0)
                .money(Money.of(35.50, "EUR"))
                .build();

        LocalDateTime testDate = LocalDateTime.of(2020, 9, 15, 12, 0);

        assertTrue(price.isApplicableOn(testDate));
    }

    @Test
    void shouldReturnFalseWhenDateIsNotApplicable() {
        Price price = Price.builder()
                .brandId(BrandId.of(1))
                .productId(ProductId.of(35455L))
                .validityPeriod(DateRange.of(
                        LocalDateTime.of(2020, 6, 14, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59)))
                .priceList(1L)
                .priority(0)
                .money(Money.of(35.50, "EUR"))
                .build();

        LocalDateTime testDate = LocalDateTime.of(2021, 1, 1, 0, 0);

        assertFalse(price.isApplicableOn(testDate));
    }

    @Test
    void shouldReturnTrueWhenPriceHasHigherPriority() {
        Price highPriority = Price.builder()
                .brandId(BrandId.of(1))
                .productId(ProductId.of(35455L))
                .validityPeriod(DateRange.of(
                        LocalDateTime.of(2020, 6, 14, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59)))
                .priceList(1L)
                .priority(5)
                .money(Money.of(35.50, "EUR"))
                .build();

        Price lowPriority = Price.builder()
                .brandId(BrandId.of(1))
                .productId(ProductId.of(35455L))
                .validityPeriod(DateRange.of(
                        LocalDateTime.of(2020, 6, 14, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59)))
                .priceList(2L)
                .priority(1)
                .money(Money.of(25.50, "EUR"))
                .build();

        assertTrue(highPriority.hasHigherPriorityThan(lowPriority));
        assertFalse(lowPriority.hasHigherPriorityThan(highPriority));
    }

    @Test
    void shouldBeEqualWhenSameData() {
        BrandId brandId = BrandId.of(1);
        ProductId productId = ProductId.of(35455L);
        DateRange validityPeriod = DateRange.of(
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59));
        Money money = Money.of(35.50, "EUR");

        Price price1 = Price.builder()
                .brandId(brandId)
                .productId(productId)
                .validityPeriod(validityPeriod)
                .priceList(1L)
                .priority(0)
                .money(money)
                .build();

        Price price2 = Price.builder()
                .brandId(brandId)
                .productId(productId)
                .validityPeriod(validityPeriod)
                .priceList(1L)
                .priority(0)
                .money(money)
                .build();

        assertEquals(price1, price2);
        assertEquals(price1.hashCode(), price2.hashCode());
    }
}
