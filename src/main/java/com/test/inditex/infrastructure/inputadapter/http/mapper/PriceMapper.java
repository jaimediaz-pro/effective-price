package com.test.inditex.infrastructure.inputadapter.http.mapper;

import com.test.inditex.domain.Price;
import com.test.inditex.infrastructure.inputadapter.http.dto.PriceDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mapper between domain Price and HTTP PriceDTO.
 */
@Component
public class PriceMapper {

    /**
     * Converts a domain Price entity to a PriceDTO for HTTP responses.
     */
    public PriceDTO toDTO(Price price, LocalDateTime effectiveDate) {
        if (price == null) {
            return null;
        }

        return new PriceDTO(
                price.getProductId().value(),
                price.getBrandId().value(),
                price.getPriceList(),
                price.getValidityPeriod().startDate(),
                price.getValidityPeriod().endDate(),
                effectiveDate,
                price.getMoney().amount().doubleValue(),
                price.getMoney().currency());
    }
}
