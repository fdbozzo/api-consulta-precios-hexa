package com.fdbozzo.pricing.domain.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Price {

  private Integer productId;
  private Brand brand;
  private BigDecimal value;
  private Integer priceList;
  private String curr;

}
