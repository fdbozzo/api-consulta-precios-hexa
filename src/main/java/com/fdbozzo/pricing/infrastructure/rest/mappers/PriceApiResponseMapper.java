package com.fdbozzo.pricing.infrastructure.rest.mappers;

import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.infrastructure.rest.model.PriceApiResponse;

public final class PriceApiResponseMapper {

  private PriceApiResponseMapper() {}

  public static PriceApiResponse toResponse(Price price) {
    PriceApiResponse priceApiResponse = new PriceApiResponse();
    priceApiResponse.setPrice(price.value());
    priceApiResponse.setPriceList(price.priceList());
    priceApiResponse.setBrandId(price.brand().id());
    priceApiResponse.setProductId(price.productId());
    priceApiResponse.setCurr(price.curr());
    priceApiResponse.setStartDate(price.startDate());
    priceApiResponse.setEndDate(price.endDate());
    return priceApiResponse;
  }

}
