package com.fdbozzo.pricing.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fdbozzo.pricing.domain.exceptions.DomainException;
import com.fdbozzo.pricing.domain.model.Brand;
import com.fdbozzo.pricing.domain.model.ErrorCode;
import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.domain.ports.in.GetPriceUseCase;
import com.fdbozzo.pricing.infrastructure.rest.PricesApiController;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PricesApiController.class)
class PricesApiIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private GetPriceUseCase getPriceUseCase;

  @Test
  @DisplayName("Should return 200 and price details when price is found")
  void shouldReturnPriceWhenFound() throws Exception {
    // Given
    Integer brandId = 1;
    Integer productId = 35455;

    Price mockPrice = new Price(
        productId,
        new Brand(1, "Zara"),
        LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0),
        LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59),
        new BigDecimal("35.50"),
        1,
        "EUR"
    );

    when(getPriceUseCase.getPrice(eq(brandId), eq(productId), any(LocalDateTime.class)))
        .thenReturn(mockPrice);

    // When & Then
    mockMvc.perform(get("/v1/prices")
            .param("brand_id", brandId.toString())
            .param("product_id", productId.toString())
            .param("application_datetime", "2020-06-14T10:00:00")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brand_id").value(1))
        .andExpect(jsonPath("$.product_id").value(35455))
        .andExpect(jsonPath("$.price").value(35.50))
        .andExpect(jsonPath("$.price_list").value(1))
        .andExpect(jsonPath("$.curr").value("EUR"));
  }

  @Test
  @DisplayName("Should return 404 when use case throws PRICE_NOT_FOUND")
  void shouldReturn404WhenNotFound() throws Exception {
    // Given
    when(getPriceUseCase.getPrice(any(), any(), any()))
        .thenThrow(new DomainException(ErrorCode.PRICE_NOT_FOUND, "Price not found"));

    // When & Then
    mockMvc.perform(get("/v1/prices")
            .param("brand_id", "1")
            .param("product_id", "35455")
            .param("application_datetime", "2026-06-14T10:00:00"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.code").value("PRICE_NOT_FOUND"));
  }

  @Test
  @DisplayName("Should return 400 when parameters are missing")
  void shouldReturn400WhenMissingParams() throws Exception {
    mockMvc.perform(get("/v1/prices")
            .param("brand_id", "1"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Should return 400 when date format is invalid")
  void shouldReturn400WhenInvalidDate() throws Exception {
    mockMvc.perform(get("/v1/prices")
            .param("brand_id", "1")
            .param("product_id", "35455")
            .param("application_datetime", "invalid-date"))
        .andExpect(status().isBadRequest());
  }
}
