package com.test.inditex.infrastructure.outputadapter.h2repository;

import com.test.inditex.domain.Price;
import com.test.inditex.infrastructure.exceptions.EffectivePriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class H2RepositoryTest {

    @InjectMocks
    H2Repository h2Repository;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    void getEffectivePrice() {
        // test for price obtained with effective date
        List<Price> priceList = new ArrayList<>();
        Price price = Price.builder().brandId(2).priceList(3L).productId(4L).price(5.0).currency("USD").build();
        priceList.add(price);
        Mockito.when(jdbcTemplate.query(anyString(), Mockito.any(RowMapper.class))).thenReturn(priceList);
        assertEquals(price, h2Repository.getEffectivePrice(new Timestamp(System.currentTimeMillis()), 4L, 2));
    }

    @Test
    void getEffectivePrice2() {
        // test for price obtained with effective date
        List<Price> priceList = new ArrayList<>();
        Mockito.when(jdbcTemplate.query(anyString(), Mockito.any(RowMapper.class))).thenReturn(priceList);
        assertThrows(EffectivePriceNotFoundException.class, () -> h2Repository.getEffectivePrice(new Timestamp(System.currentTimeMillis()), 4L, 2));
    }

}