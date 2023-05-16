package com.test.inditex.infrastructure.inputadapter.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class PriceAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1() throws Exception {
        mockMvc.perform(get("/price/get")
                        .param("effectiveDate", "2020-06-14 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("effectiveDate").value("2020-06-14 10:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("priceList").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("price").value(35.5))
                .andExpect(MockMvcResultMatchers.jsonPath("currency").value("EUR"));
    }

    @Test
    public void test2() throws Exception {
        mockMvc.perform(get("/price/get")
                        .param("effectiveDate", "2020-06-14 16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("effectiveDate").value("2020-06-14 16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("priceList").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("price").value(25.45))
                .andExpect(MockMvcResultMatchers.jsonPath("currency").value("EUR"));
    }

    @Test
    public void test3() throws Exception {
        mockMvc.perform(get("/price/get")
                        .param("effectiveDate", "2020-06-14 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("effectiveDate").value("2020-06-14 21:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("priceList").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("price").value(35.5))
                .andExpect(MockMvcResultMatchers.jsonPath("currency").value("EUR"));
    }

    @Test
    public void test4() throws Exception {
        mockMvc.perform(get("/price/get")
                        .param("effectiveDate", "2020-06-15 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("effectiveDate").value("2020-06-15 10:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("priceList").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("price").value(30.5))
                .andExpect(MockMvcResultMatchers.jsonPath("currency").value("EUR"));
    }

    @Test
    public void test5() throws Exception {
        mockMvc.perform(get("/price/get")
                        .param("effectiveDate", "2020-06-16 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("effectiveDate").value("2020-06-16 21:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("priceList").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("price").value(38.95))
                .andExpect(MockMvcResultMatchers.jsonPath("currency").value("EUR"));
    }
}