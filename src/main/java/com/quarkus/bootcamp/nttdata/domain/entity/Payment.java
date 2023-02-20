package com.quarkus.bootcamp.nttdata.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Payment {
  protected Long id;
  protected Double amount;
  protected String description;
  protected Long productId;
  protected String date;
}
