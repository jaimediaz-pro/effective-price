package com.test.inditex.application.usecase;

import com.test.inditex.domain.Price;
import com.test.inditex.infrastructure.outputport.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceUseCaseTest {

    @InjectMocks
    PriceUseCase priceUseCase;

    @Mock
    PriceRepository priceRepository;

    @Test
    void getEffectivePrice() {
        Price price = Price.builder().brandId(2).priceList(3L).productId(4L).price(5.0).currency("USD").build();
        when(priceRepository.getEffectivePrice(any(Timestamp.class), anyLong(), anyInt())).thenReturn(price);

        assertEquals(price, priceUseCase.getEffectivePrice(new Timestamp(System.currentTimeMillis()), 4L, 2));
    }
}