package com.fdbozzo.pricing.unit.application.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fdbozzo.pricing.domain.model.Price;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PricesApiServiceTest {

  @Autowired
  PricesApiService service;

  @Test
  void should_retrieve_highest_priority_when_overlapping_dates() {
    Price result = service.getPrice(1, 35455, LocalDateTime.of(2020, 6, 15, 16, 0, 0));
    assertEquals(new BigDecimal("38.95"), result.price());
  }

}
