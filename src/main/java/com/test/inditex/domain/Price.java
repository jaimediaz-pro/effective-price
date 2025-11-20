package com.test.inditex.domain;

import com.test.inditex.domain.valueobject.BrandId;
import com.test.inditex.domain.valueobject.DateRange;
import com.test.inditex.domain.valueobject.Money;
import com.test.inditex.domain.valueobject.ProductId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;

/**
 * Domain entity representing a Price.
 * Immutable and rich in domain logic.
 */
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Price {

    private final BrandId brandId;
    private final ProductId productId;
    private final DateRange validityPeriod;
    private final Long priceList;
    private final Integer priority;
    private final Money money;

    /**
     * Checks if this price is applicable for the given date.
     */
    public boolean isApplicableOn(LocalDateTime date) {
        return validityPeriod.contains(date);
    }

    /**
     * Compares priority with another price.
     * Returns true if this price has higher priority.
     */
    public boolean hasHigherPriorityThan(Price other) {
        return this.priority > other.priority;
    }

    // Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BrandId brandId;
        private ProductId productId;
        private DateRange validityPeriod;
        private Long priceList;
        private Integer priority;
        private Money money;

        public Builder brandId(BrandId brandId) {
            this.brandId = brandId;
            return this;
        }

        public Builder productId(ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder validityPeriod(DateRange validityPeriod) {
            this.validityPeriod = validityPeriod;
            return this;
        }

        public Builder priceList(Long priceList) {
            this.priceList = priceList;
            return this;
        }

        public Builder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        public Builder money(Money money) {
            this.money = money;
            return this;
        }

        public Price build() {
            validate();
            return new Price(brandId, productId, validityPeriod, priceList, priority, money);
        }

        private void validate() {
            if (isNull(brandId)) {
                throw new IllegalArgumentException("BrandId cannot be null");
            }
            if (isNull(productId)) {
                throw new IllegalArgumentException("ProductId cannot be null");
            }
            if (isNull(validityPeriod)) {
                throw new IllegalArgumentException("Validity period cannot be null");
            }
            if (isNull(priceList) || priceList <= 0) {
                throw new IllegalArgumentException("Price list must be positive");
            }
            if (isNull(priority) || priority < 0) {
                throw new IllegalArgumentException("Priority cannot be null or negative");
            }
            if (isNull(money)) {
                throw new IllegalArgumentException("Money cannot be null");
            }
        }
    }
}
