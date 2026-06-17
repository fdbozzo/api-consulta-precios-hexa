package com.fdbozzo.pricing.unit.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdbozzo.pricing.domain.exceptions.DomainException;
import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.infrastructure.persistence.PriceRepository;
import com.fdbozzo.pricing.infrastructure.persistence.PriceRepositoryAdapter;
import com.fdbozzo.pricing.infrastructure.persistence.entities.BrandEntity;
import com.fdbozzo.pricing.infrastructure.persistence.entities.PriceEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private PriceRepositoryAdapter priceRepositoryAdapter;

  @Test
  void should_return_price_when_found() {
    // Given
    Integer brandId = 1;
    Integer productId = 35455;
    LocalDateTime applicationDatetime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

    BrandEntity brandEntity = new BrandEntity();
    brandEntity.setId(brandId);
    brandEntity.setName("ZARA");

    PriceEntity priceEntity = new PriceEntity();
    priceEntity.setProductId(productId);
    priceEntity.setBrand(brandEntity);
    priceEntity.setPrice(new BigDecimal("35.50"));
    priceEntity.setPriceList(1);
    priceEntity.setCurr("EUR");
    priceEntity.setStartDate(applicationDatetime.minusDays(1));
    priceEntity.setEndDate(applicationDatetime.plusDays(1));

    when(priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        brandId, productId, applicationDatetime, applicationDatetime))
        .thenReturn(Optional.of(priceEntity));

    // When
    Price result = priceRepositoryAdapter.getPrice(brandId, productId, applicationDatetime);

    // Then
    assertEquals(productId, result.productId());
    assertEquals(new BigDecimal("35.50"), result.value());
    assertEquals(brandId, result.brand().id());
    verify(priceRepository).findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        brandId, productId, applicationDatetime, applicationDatetime);
  }

  @Test
  void should_throw_exception_when_not_found() {
    // Given
    Integer brandId = 1;
    Integer productId = 35455;
    LocalDateTime applicationDatetime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

    when(priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        brandId, productId, applicationDatetime, applicationDatetime))
        .thenReturn(Optional.empty());

    // When & Then
    assertThrows(DomainException.class, () ->
        priceRepositoryAdapter.getPrice(brandId, productId, applicationDatetime));
  }
}
