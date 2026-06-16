package com.fdbozzo.pricing.domain.ports.in;

import com.fdbozzo.pricing.domain.model.Price;
import java.time.LocalDateTime;

public interface GetPriceUseCase {

  Price getPrice(Integer brandId, Integer productId, LocalDateTime applicationDatetime);

}
