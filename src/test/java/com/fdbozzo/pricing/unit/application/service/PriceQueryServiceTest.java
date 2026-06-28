package com.fdbozzo.pricing.unit.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdbozzo.pricing.application.service.PriceQueryService;
import com.fdbozzo.pricing.domain.model.Brand;
import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.domain.ports.out.PriceRepositoryPort;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PriceQueryServiceTest {

  @Mock
  private PriceRepositoryPort priceRepositoryPort;

  @InjectMocks
  private PriceQueryService priceQueryService;

  @Test
  void should_call_repository_with_correct_parameters_and_return_price() {
    // Given
    Integer brandId = 1;
    Integer productId = 35455;
    LocalDateTime applicationDatetime = LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0);
    Price expectedPrice = new Price(productId, new Brand(brandId, "ZARA"), applicationDatetime,
        applicationDatetime, new BigDecimal("35.50"), 1, "EUR");

    when(priceRepositoryPort.getPrice(brandId, productId, applicationDatetime))
        .thenReturn(expectedPrice);

    // When
    Price result = priceQueryService.getPrice(brandId, productId, applicationDatetime);

    // Then
    assertEquals(expectedPrice, result);
    verify(priceRepositoryPort).getPrice(brandId, productId, applicationDatetime);
  }

}
