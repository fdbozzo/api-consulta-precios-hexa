package com.fdbozzo.pricing.infra.adapter.out.mapper;

import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.infra.adapter.out.model.PriceEntity;

public final class PriceEntityMapper {

  private PriceEntityMapper() {}

  public static Price toDomain(PriceEntity priceEntity) {
    if (priceEntity == null) {
      return null;
    }
    final Price price = new Price();
    price.setValue(priceEntity.getPrice());
    price.setBrand(BrandEntityMapper.toDomain(priceEntity.getBrand()));
    price.setPriceList(priceEntity.getPriceList());
    price.setCurr(priceEntity.getCurr());
    return price;
  }

}
