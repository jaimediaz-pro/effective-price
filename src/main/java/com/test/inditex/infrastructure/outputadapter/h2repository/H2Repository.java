package com.test.inditex.infrastructure.outputadapter.h2repository;

import com.test.inditex.domain.Price;
import com.test.inditex.infrastructure.exceptions.EffectivePriceNotFoundException;
import com.test.inditex.infrastructure.outputport.PriceRepository;
import com.test.inditex.infrastructure.rowmapper.EffectivePriceRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Component("h2repository")
public class H2Repository implements PriceRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public H2Repository(@Qualifier("h2DataSource") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Price getEffectivePrice(Timestamp effectiveDate, Long productId, int brandId) {
        List<Price> effectivePriceList = jdbcTemplate.query("SELECT * FROM prices where START_DATE < TIMESTAMP '"+effectiveDate+"' AND END_DATE > TIMESTAMP '"+effectiveDate+"' AND PRODUCT_ID = "+productId+" AND BRAND_ID = "+brandId+ " ORDER BY PRIORITY DESC LIMIT 1", new EffectivePriceRowMapper());
        if(effectivePriceList.size()<1)
            throw new EffectivePriceNotFoundException("Effective price not found in database");

        Price effectivePrice = effectivePriceList.get(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = formatter.format(effectiveDate);
        effectivePrice.setEffectiveDate(formattedTimestamp);
        return effectivePrice;
    }
}
