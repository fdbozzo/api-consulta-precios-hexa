package com.fdbozzo.pricing.infrastructure.rest;

import com.fdbozzo.pricing.application.ports.in.GetPriceUseCase;
import com.fdbozzo.pricing.infrastructure.rest.api.PricesApi;
import com.fdbozzo.pricing.infrastructure.rest.mappers.PriceApiResponseMapper;
import com.fdbozzo.pricing.infrastructure.rest.model.PriceApiResponse;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PricesApiController implements PricesApi {

  private final GetPriceUseCase getPriceUseCase;

  public PricesApiController(GetPriceUseCase getPriceUseCase) {
    this.getPriceUseCase = getPriceUseCase;
  }

  @Override
  public ResponseEntity<PriceApiResponse> v1PricesGet(Integer brandId, Integer productId,
      LocalDateTime applicationDatetime) {
    if (log.isDebugEnabled()) {
      log.debug("Fetching price for Brand ID:('{}', Product ID:'{}', Date:'{}')", brandId, productId, applicationDatetime);
    }
    return ResponseEntity.ok(PriceApiResponseMapper.toResponse(
        getPriceUseCase.getPrice(brandId, productId, applicationDatetime)));
  }

}
