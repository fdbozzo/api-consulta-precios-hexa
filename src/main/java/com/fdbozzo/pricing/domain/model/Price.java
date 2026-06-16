package com.fdbozzo.pricing.domain.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Price {

  private Brand brand;
  private BigDecimal price;
  private Integer priceList;
  private CurrencyType curr;

  public enum CurrencyType {
    EUR, USD, GBP
  }

}
