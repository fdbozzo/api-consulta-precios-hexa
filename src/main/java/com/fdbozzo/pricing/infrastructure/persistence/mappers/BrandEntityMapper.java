package com.fdbozzo.pricing.infrastructure.persistence.mappers;

import com.fdbozzo.pricing.domain.model.Brand;
import com.fdbozzo.pricing.infrastructure.persistence.entities.BrandEntity;

public final class BrandEntityMapper {

  private BrandEntityMapper() {}

  public static Brand toDomain(BrandEntity brandEntity) {
    if (brandEntity == null) {
      return null;
    }
    final Brand brand = new Brand();
    brand.setId(brandEntity.getId());
    brand.setName(brandEntity.getName());
    return brand;
  }

}
