package com.fdbozzo.pricing.infrastructure.persistence.mappers;

import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.infrastructure.persistence.entities.PriceEntity;

public final class PriceEntityMapper {

  private PriceEntityMapper() {}

  public static Price toDomain(PriceEntity priceEntity) {
    if (priceEntity == null) {
      return null;
    }
    return new Price(priceEntity.getProductId(), BrandEntityMapper.toDomain(priceEntity.getBrand()),
        priceEntity.getStartDate(), priceEntity.getEndDate(), priceEntity.getPrice(),
        priceEntity.getPriceList(), priceEntity.getCurr());
  }

}
