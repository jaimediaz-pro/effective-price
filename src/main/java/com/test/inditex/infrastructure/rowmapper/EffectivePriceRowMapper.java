package com.test.inditex.infrastructure.rowmapper;

import com.test.inditex.domain.Price;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EffectivePriceRowMapper implements RowMapper<Price> {
    @Override
    public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Price.builder()
                .brandId(rs.getInt("BRAND_ID"))
                .priceList(rs.getLong("PRICE_LIST"))
                .productId(rs.getLong("PRODUCT_ID"))
                .price(rs.getDouble("PRICE"))
                .currency(rs.getString("CURR"))
                .build();
    }
}
