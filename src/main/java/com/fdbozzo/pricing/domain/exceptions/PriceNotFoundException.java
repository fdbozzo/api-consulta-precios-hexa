package com.fdbozzo.pricing.domain.exceptions;

import java.time.LocalDateTime;

public class PriceNotFoundException extends DomainException {

  public PriceNotFoundException(Integer brandId, Integer productId, LocalDateTime date) {
    super(
        "PRICE_NOT_FOUND",
        String.format("Price not found for brandId=%s productId=%s date=%s",
            brandId, productId, date)
    );
  }

}
