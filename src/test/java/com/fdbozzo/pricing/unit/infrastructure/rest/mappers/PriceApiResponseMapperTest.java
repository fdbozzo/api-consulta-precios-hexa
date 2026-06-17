package com.fdbozzo.pricing.unit.infrastructure.rest.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fdbozzo.pricing.domain.model.Brand;
import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.infrastructure.rest.mappers.PriceApiResponseMapper;
import com.fdbozzo.pricing.infrastructure.rest.model.PriceApiResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PriceApiResponseMapperTest {

  @Test
  void should_map_price_to_response() {
    // Given
    Brand brand = new Brand(1, "ZARA");

    Price price = new Price(35455, brand,
        LocalDateTime.of(2020, 6, 14, 0, 0, 0),
        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
        new BigDecimal("35.50"), 1, "EUR");

    // When
    PriceApiResponse response = PriceApiResponseMapper.toResponse(price);

    // Then
    assertNotNull(response);
    assertEquals(price.productId(), response.getProductId());
    assertEquals(price.brand().id(), response.getBrandId());
    assertEquals(price.value(), response.getPrice());
    assertEquals(price.priceList(), response.getPriceList());
    assertEquals(price.curr(), response.getCurr());
    assertEquals(price.startDate(), response.getStartDate());
    assertEquals(price.endDate(), response.getEndDate());
  }
}
