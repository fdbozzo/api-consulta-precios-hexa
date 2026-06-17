package com.fdbozzo.pricing.infrastructure.persistence.mappers;

import com.fdbozzo.pricing.domain.model.Brand;
import com.fdbozzo.pricing.infrastructure.persistence.entities.BrandEntity;

public final class BrandEntityMapper {

  private BrandEntityMapper() {}

  public static Brand toDomain(BrandEntity brandEntity) {
    if (brandEntity == null) {
      return null;
    }
    return new Brand(brandEntity.getId(), brandEntity.getName());
  }

}
