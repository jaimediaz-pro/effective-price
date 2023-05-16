package com.test.inditex.infrastructure.inputadapter.http;

import com.test.inditex.domain.Price;
import com.test.inditex.infrastructure.inputport.PriceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping(value = "price")
public class PriceAPI {

    @Autowired
    PriceInputPort priceInputPort;

    @GetMapping(value = "get", produces= MediaType.APPLICATION_JSON_VALUE)
    public Price getEffectivePrice(@RequestParam Timestamp effectiveDate, @RequestParam Long productId, @RequestParam int brandId) {
        return priceInputPort.getEffectivePrice(effectiveDate,productId,brandId);
    }

}
