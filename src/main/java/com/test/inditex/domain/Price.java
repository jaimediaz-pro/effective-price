package com.test.inditex.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price {
    private Integer brandId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String effectiveDate;
    private Long priceList;
    private Long productId;
    private Integer priority;
    private Double price;
    private String currency;
}
