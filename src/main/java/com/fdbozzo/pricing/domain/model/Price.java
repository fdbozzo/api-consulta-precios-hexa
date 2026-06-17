package com.fdbozzo.pricing.domain.model;

import com.fdbozzo.pricing.domain.exceptions.DomainException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price (
  Integer productId,
  Brand brand,
  LocalDateTime startDate,
  LocalDateTime endDate,
  BigDecimal value,
  Integer priceList,
  String curr
)
{
  public Price {
    if (value == null) {
      throw new DomainException(ErrorCode.PRICE_NULL, "Price cannot be null");
    }

    if (value.signum() < 0) {
      throw new DomainException(ErrorCode.PRICE_NEGATIVE, "Price cannot be negative [" + value + "]");
    }
  }
}
