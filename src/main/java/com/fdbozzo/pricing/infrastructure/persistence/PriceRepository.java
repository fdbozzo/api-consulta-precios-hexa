package com.fdbozzo.pricing.infrastructure.persistence;

import com.fdbozzo.pricing.infrastructure.persistence.entities.PriceEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

  /**
   * Equivalent query (documentation):
   * Query(""" SELECT p FROM PriceEntity p WHERE p.brand.id =
   * :brandId AND p.productId = :productId AND p.startDate <= :date AND p.endDate >= :date ORDER BY
   * p.priority DESC """)
   */
  List<PriceEntity> findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
      Integer brandId, Integer productId, LocalDateTime appDatetimeStart, LocalDateTime appDatetimeEnd);

}
