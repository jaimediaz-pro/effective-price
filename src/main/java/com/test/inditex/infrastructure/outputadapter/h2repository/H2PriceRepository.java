package com.test.inditex.infrastructure.outputadapter.h2repository;

import com.test.inditex.domain.Price;
import com.test.inditex.domain.repository.PriceRepository;
import com.test.inditex.domain.valueobject.BrandId;
import com.test.inditex.domain.valueobject.DateRange;
import com.test.inditex.domain.valueobject.Money;
import com.test.inditex.domain.valueobject.ProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component("h2PriceRepository")
public class H2PriceRepository implements PriceRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public H2PriceRepository(@Qualifier("h2DataSource") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Price> findEffectivePrice(LocalDateTime effectiveDate, ProductId productId, BrandId brandId) {
        String sql = "SELECT * FROM prices " +
                "WHERE START_DATE < ? " +
                "AND END_DATE > ? " +
                "AND PRODUCT_ID = ? " +
                "AND BRAND_ID = ? " +
                "ORDER BY PRIORITY DESC " +
                "LIMIT 1";

        Timestamp effectiveTimestamp = Timestamp.valueOf(effectiveDate);

        var results = jdbcTemplate.query(
                sql,
                new PriceRowMapper(),
                effectiveTimestamp,
                effectiveTimestamp,
                productId.getValue(),
                brandId.getValue());

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    /**
     * Row mapper that converts database rows to domain Price entities.
     */
    private static class PriceRowMapper implements RowMapper<Price> {
        @Override
        public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
            // Extract data from ResultSet
            Integer brandId = rs.getInt("BRAND_ID");
            Long productId = rs.getLong("PRODUCT_ID");
            Timestamp startDate = rs.getTimestamp("START_DATE");
            Timestamp endDate = rs.getTimestamp("END_DATE");
            Long priceList = rs.getLong("PRICE_LIST");
            Integer priority = rs.getInt("PRIORITY");
            Double priceAmount = rs.getDouble("PRICE");
            String currency = rs.getString("CURR");

            // Convert to Value Objects
            BrandId brandIdVO = BrandId.of(brandId);
            ProductId productIdVO = ProductId.of(productId);
            DateRange validityPeriod = DateRange.of(
                    startDate.toLocalDateTime(),
                    endDate.toLocalDateTime());
            Money money = Money.of(priceAmount, currency);

            // Build domain entity
            return Price.builder()
                    .brandId(brandIdVO)
                    .productId(productIdVO)
                    .validityPeriod(validityPeriod)
                    .priceList(priceList)
                    .priority(priority)
                    .money(money)
                    .build();
        }
    }
}
