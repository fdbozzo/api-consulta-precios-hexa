package com.fdbozzo.pricing.e2e.price;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.path.json.config.JsonPathConfig;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class GetPriceE2ETest {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssured.port = port;
    config = config()
        .jsonConfig(JsonConfig.jsonConfig()
            .numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));
  }

  @ParameterizedTest
  @MethodSource("priceCases")
  void should_return_expected_price(String qryDatetime,
      Integer expectedPriceList,
      BigDecimal expectedPrice) {
    given()
        .queryParam("brand_id", 1)
        .queryParam("product_id", 35455)
        .queryParam("application_datetime", qryDatetime)

        .when()
        .get("/v1/prices")

        .then()
        .statusCode(200)
        .body("price_list", equalTo(expectedPriceList))
        .body("price", equalTo(expectedPrice));

  }

  @Test
  void should_return_404_when_price_is_not_found() {

    given()
        .queryParam("application_datetime", "2026-01-01T10:00:00")
        .queryParam("product_id", 99999)
        .queryParam("brand_id", 1)

        .when()
        .get("/v1/prices")

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
        Arguments.of("2020-06-14T10:00:00", 1, new BigDecimal("35.50")),
        Arguments.of("2020-06-14T16:00:00", 2, new BigDecimal("25.45")),
        Arguments.of("2020-06-14T21:00:00", 1, new BigDecimal("35.50")),
        Arguments.of("2020-06-15T10:00:00", 3, new BigDecimal("30.50")),
        Arguments.of("2020-06-16T21:00:00", 4, new BigDecimal("38.95"))
    );
  }
}
