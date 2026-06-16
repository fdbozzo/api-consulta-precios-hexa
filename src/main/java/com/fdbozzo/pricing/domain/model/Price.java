package com.fdbozzo.pricing.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Price {

  private Integer productId;
  private Brand brand;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private BigDecimal value;
  private Integer priceList;
  private String curr;

}
