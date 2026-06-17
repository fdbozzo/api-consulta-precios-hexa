package com.fdbozzo.pricing.infrastructure.persistence.mappers;

import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.infrastructure.persistence.entities.PriceEntity;

public final class PriceEntityMapper {

  private PriceEntityMapper() {}

  public static Price toDomain(PriceEntity priceEntity) {
    if (priceEntity == null) {
      return null;
    }
    final Price price = new Price();
    price.setValue(priceEntity.getPrice());
    price.setBrand(BrandEntityMapper.toDomain(priceEntity.getBrand()));
    price.setProductId(priceEntity.getProductId());
    price.setPriceList(priceEntity.getPriceList());
    price.setCurr(priceEntity.getCurr());
    price.setStartDate(priceEntity.getStartDate());
    price.setEndDate(priceEntity.getEndDate());
    return price;
  }

}
