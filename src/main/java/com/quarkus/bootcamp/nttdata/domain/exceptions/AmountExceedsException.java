package com.quarkus.bootcamp.nttdata.domain.exceptions;

public class AmountExceedsException extends Exception {
  public AmountExceedsException(String errorMessage) {
    super(errorMessage);
  }
}
