package com.fdbozzo.pricing.domain.model;

public class Price {

  private Brand brand;
  private Double price;
  private Integer priceList;
  private CurrencyType curr;

  public enum CurrencyType {
    EUR, USD, GBP
  }

}
