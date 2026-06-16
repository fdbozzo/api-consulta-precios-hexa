package com.fdbozzo.pricing.application.service;

import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.domain.ports.in.GetPriceUseCase;
import com.fdbozzo.pricing.domain.ports.out.PriceRepositoryPort;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class PriceQueryService implements GetPriceUseCase {

  private final PriceRepositoryPort priceRepositoryPort;

  public PriceQueryService(PriceRepositoryPort priceRepositoryPort) {
    this.priceRepositoryPort = priceRepositoryPort;
  }

  @Override
  public Price getPrice(Integer brandId, Integer productId, LocalDateTime applicationDatetime) {
    return priceRepositoryPort.getPrice(brandId, productId, applicationDatetime);
  }

}
