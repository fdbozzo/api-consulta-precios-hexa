package com.fdbozzo.pricing.infrastructure.persistence;

import com.fdbozzo.pricing.domain.exceptions.DomainException;
import com.fdbozzo.pricing.domain.model.ErrorCode;
import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.domain.ports.out.PriceRepositoryPort;
import com.fdbozzo.pricing.infrastructure.persistence.entities.PriceEntity;
import com.fdbozzo.pricing.infrastructure.persistence.mappers.PriceEntityMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private final PriceRepository priceRepository;

  public PriceRepositoryAdapter(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  @Override
  public Price getPrice(Integer brandId, Integer productId, LocalDateTime applicationDatetime) {
    Optional<PriceEntity> result = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        brandId, productId, applicationDatetime, applicationDatetime);
    return result
        .map(PriceEntityMapper::toDomain)
        .orElseThrow(() -> new DomainException(ErrorCode.PRICE_NOT_FOUND,
            String.format("Price not found for brandId=%s productId=%s date=%s",
                brandId, productId, applicationDatetime)));
  }

}
