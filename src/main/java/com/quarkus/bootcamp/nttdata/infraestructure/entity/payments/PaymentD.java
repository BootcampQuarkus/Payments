package com.quarkus.bootcamp.nttdata.infraestructure.entity.payments;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
public class PaymentD extends PanacheEntity {
  protected Double amount;
  protected String description;
  protected Long productId;
  @Column(nullable = true)
  protected String createdAt = null;
  /**
   * Fecha de la ultima actualización la linea de crédito.
   */
  @Column(nullable = true)
  protected String updatedAt = null;
  /**
   * Fecha en la que se eliminó la linea de crédito.
   */
  @Column(nullable = true)
  protected String deletedAt = null;
}
