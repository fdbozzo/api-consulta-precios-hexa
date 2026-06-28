package com.fdbozzo.pricing.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.fdbozzo.pricing.infrastructure.persistence.PriceRepository;
import com.fdbozzo.pricing.infrastructure.persistence.entities.PriceEntity;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class PriceRepositoryIntegrationTest {

  @Autowired
  private PriceRepository priceRepository;

  @Test
  @DisplayName("Should find the correct price for a given brand, product, and date")
  void shouldFindPriceForGivenDate() {
    // Given
    Integer brandId = 1;
    Integer productId = 35455;
    LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0);

    // When
    Optional<PriceEntity> result = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        brandId, productId, applicationDate, applicationDate);

    // Then
    assertThat(result).isPresent();
    assertThat(result.get().getPriceList()).isEqualTo(1);
    assertThat(result.get().getPrice()).isEqualByComparingTo("35.50");
  }

  @Test
  @DisplayName("Should find the highest priority price when multiple overlap")
  void shouldFindHighestPriorityPrice() {
    // Given
    Integer brandId = 1;
    Integer productId = 35455;
    // At this time, both price_list 1 (priority 0) and price_list 2 (priority 1) are active
    LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 16, 0, 0);

    // When
    Optional<PriceEntity> result = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        brandId, productId, applicationDate, applicationDate);

    // Then
    assertThat(result).isPresent();
    assertThat(result.get().getPriceList()).isEqualTo(2);
    assertThat(result.get().getPriority()).isEqualTo(1);
    assertThat(result.get().getPrice()).isEqualByComparingTo("25.45");
  }

  @Test
  @DisplayName("Should return empty when no price matches criteria")
  void shouldReturnEmptyWhenNoPriceFound() {
    // Given
    Integer brandId = 1;
    Integer productId = 35455;
    LocalDateTime applicationDate = LocalDateTime.of(2026, Month.JANUARY, 1, 0, 0, 0);

    // When
    Optional<PriceEntity> result = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        brandId, productId, applicationDate, applicationDate);

    // Then
    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("Should return empty for non-existent brand")
  void shouldReturnEmptyForNonExistentBrand() {
    // Given
    Integer brandId = 99;
    Integer productId = 35455;
    LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0);

    // When
    Optional<PriceEntity> result = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        brandId, productId, applicationDate, applicationDate);

    // Then
    assertThat(result).isEmpty();
  }
}
