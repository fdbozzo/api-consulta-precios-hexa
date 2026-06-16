package com.fdbozzo.pricing.application.service;

import com.fdbozzo.pricing.domain.model.Price;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class PricesApiService {

  public Price getPrice(Integer brandId, Integer productId, LocalDateTime applicationDatetime) {
    return null;
  }

}
