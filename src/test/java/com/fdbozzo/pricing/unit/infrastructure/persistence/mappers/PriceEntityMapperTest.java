package com.fdbozzo.pricing.unit.infrastructure.persistence.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.infrastructure.persistence.entities.BrandEntity;
import com.fdbozzo.pricing.infrastructure.persistence.entities.PriceEntity;
import com.fdbozzo.pricing.infrastructure.persistence.mappers.PriceEntityMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PriceEntityMapperTest {

  @Test
  void should_map_entity_to_domain() {
    // Given
    BrandEntity brandEntity = new BrandEntity();
    brandEntity.setId(1);
    brandEntity.setName("ZARA");

    PriceEntity entity = new PriceEntity();
    entity.setProductId(35455);
    entity.setBrand(brandEntity);
    entity.setPrice(new BigDecimal("35.50"));
    entity.setPriceList(1);
    entity.setCurr("EUR");
    entity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
    entity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));

    // When
    Price domain = PriceEntityMapper.toDomain(entity);

    // Then
    assertNotNull(domain);
    assertEquals(entity.getProductId(), domain.productId());
    assertEquals(entity.getBrand().getId(), domain.brand().id());
    assertEquals(entity.getPrice(), domain.value());
    assertEquals(entity.getPriceList(), domain.priceList());
    assertEquals(entity.getCurr(), domain.curr());
    assertEquals(entity.getStartDate(), domain.startDate());
    assertEquals(entity.getEndDate(), domain.endDate());
  }

  @Test
  void should_return_null_when_entity_is_null() {
    assertNull(PriceEntityMapper.toDomain(null));
  }
}
