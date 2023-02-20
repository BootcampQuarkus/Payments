package com.quarkus.bootcamp.nttdata.domain.exceptions;

public class ProductNotFoundException extends Exception {
  public ProductNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
