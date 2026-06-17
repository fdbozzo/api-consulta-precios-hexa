package com.fdbozzo.pricing.infrastructure.rest;

import com.fdbozzo.pricing.domain.ports.in.GetPriceUseCase;
import com.fdbozzo.pricing.infrastructure.rest.api.PricesApi;
import com.fdbozzo.pricing.infrastructure.rest.mappers.PriceApiResponseMapper;
import com.fdbozzo.pricing.infrastructure.rest.model.PriceApiResponse;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;

public class PricesApiController implements PricesApi {

  private final GetPriceUseCase getPriceUseCase;

  public PricesApiController(GetPriceUseCase getPriceUseCase) {
    this.getPriceUseCase = getPriceUseCase;
  }

  @Override
  public ResponseEntity<PriceApiResponse> v1PricesGet(Integer brandId, Integer productId,
      LocalDateTime applicationDatetime) {
    return ResponseEntity.ok(PriceApiResponseMapper.toResponse(getPriceUseCase.getPrice(brandId, productId, applicationDatetime)));
  }

}
