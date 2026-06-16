package com.fdbozzo.pricing.infrastructure.persistence;

import com.fdbozzo.pricing.domain.model.Price;
import com.fdbozzo.pricing.domain.ports.out.PriceRepositoryPort;
import com.fdbozzo.pricing.infrastructure.persistence.mappers.PriceEntityMapper;
import java.time.LocalDateTime;
import org.springframework.stereotype.Repository;

@Repository
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private final PriceRepository priceRepository;

  public PriceRepositoryAdapter(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  @Override
  public Price getPrice(Integer brandId, Integer productId, LocalDateTime applicationDatetime) {
    return PriceEntityMapper.toDomain(
        priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, applicationDatetime, applicationDatetime)
            .get(0));
  }
}
