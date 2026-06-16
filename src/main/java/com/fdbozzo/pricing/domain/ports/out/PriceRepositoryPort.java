package com.fdbozzo.pricing.domain.ports.out;

import com.fdbozzo.pricing.domain.model.Price;
import java.time.LocalDateTime;

public interface PriceRepositoryPort {

  Price getPrice(Integer brandId, Integer productId, LocalDateTime applicationDatetime);

}
