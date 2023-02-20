package com.quarkus.bootcamp.nttdata.infraestructure.entity.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditD {
  protected Long id;
  /**
   * Monto aprobado del credito al momento de crear el producto.
   */
  protected Double amount;
  /**
   * Id del cliente al que le pertenece el producto
   */
  protected Long customerId;
  /**
   * El saldo por pagar
   */
  protected Double balance;
  /**
   * Número de cuotas
   */
  protected Integer dues;
  /**
   * Fecha de pago de las cuotas (dd-mm).
   */
  protected String paymentDueDate;
}
