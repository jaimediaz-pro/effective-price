package com.test.inditex.infrastructure.inputadapter.http;

import com.test.inditex.application.port.input.PriceInputPort;
import com.test.inditex.domain.Price;
import com.test.inditex.domain.valueobject.BrandId;
import com.test.inditex.domain.valueobject.ProductId;
import com.test.inditex.infrastructure.inputadapter.http.dto.PriceDTO;
import com.test.inditex.infrastructure.inputadapter.http.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/price")
public class PriceAPI {

    private final PriceInputPort priceInputPort;
    private final PriceMapper priceMapper;

    @Autowired
    public PriceAPI(PriceInputPort priceInputPort, PriceMapper priceMapper) {
        this.priceInputPort = priceInputPort;
        this.priceMapper = priceMapper;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDTO> getEffectivePrice(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime effectiveDate,
            @RequestParam Long productId,
            @RequestParam Integer brandId) {

        // Convert primitives to Value Objects
        ProductId productIdVO = ProductId.of(productId);
        BrandId brandIdVO = BrandId.of(brandId);

        // Call use case
        Price price = priceInputPort.getEffectivePrice(effectiveDate, productIdVO, brandIdVO);

        // Convert to DTO and return
        PriceDTO dto = priceMapper.toDTO(price, effectiveDate);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
