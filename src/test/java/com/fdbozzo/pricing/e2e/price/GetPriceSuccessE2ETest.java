package com.fdbozzo.pricing.e2e.price;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GetPriceSuccessE2ETest {

  @ParameterizedTest
  @MethodSource("priceCases")
  void should_return_expected_price(String qryDatetime,
      Integer expectedPriceList,
      BigDecimal expectedPrice) {
    given()
        .queryParam("brandId", 1)
        .queryParam("productId", 35455)
        .queryParam("applicationDatetime", qryDatetime)

        .when()
        .get("/v1/price")

        .then()
        .statusCode(200)
        .body("price_list", equalTo(expectedPriceList))
        .body("price", equalTo(expectedPrice));

  }

  @Test
  void should_return_404_when_price_is_not_found() {

    given()
        .queryParam("applicationDatetime", "2026-01-01 10:00:00")
        .queryParam("productId", 99999)
        .queryParam("brandId", 1)

        .when()
        .get("/v1/price")

        .then()
        .statusCode(404);
  }

  /**
   * Generate the use cases defined on the original spec.
   *
   * @return Application datetime, Expected price list and Expected price
   */
  private static Stream<Arguments> priceCases() {
    return Stream.of(
        Arguments.of("2020-06-14 10:00:00", 1, new BigDecimal("35.50")),
        Arguments.of("2020-06-14 16:00:00", 2, new BigDecimal("25.45")),
        Arguments.of("2020-06-14 21:00:00", 1, new BigDecimal("35.50")),
        Arguments.of("2020-06-15 10:00:00", 3, new BigDecimal("30.50")),
        Arguments.of("2020-06-16 21:00:00", 4, new BigDecimal("38.95"))
    );
  }
}
