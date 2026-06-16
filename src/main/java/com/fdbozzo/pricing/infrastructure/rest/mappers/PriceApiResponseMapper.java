package com.fdbozzo.pricing.infrastructure.rest.mappers;

import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.infrastructure.rest.model.PriceApiResponse;

public final class PriceApiResponseMapper {

  private PriceApiResponseMapper() {}

  public static PriceApiResponse toResponse(Price price) {
    PriceApiResponse priceApiResponse = new PriceApiResponse();
    priceApiResponse.setPrice(price.getValue());
    priceApiResponse.setPriceList(price.getPriceList());
    priceApiResponse.setBrandId(price.getBrand().getId());
    priceApiResponse.setProductId(price.getProductId());
    priceApiResponse.setCurr(price.getCurr());
    priceApiResponse.setStartDate(price.getStartDate());
    priceApiResponse.setEndDate(price.getEndDate());
    return priceApiResponse;
  }

}
