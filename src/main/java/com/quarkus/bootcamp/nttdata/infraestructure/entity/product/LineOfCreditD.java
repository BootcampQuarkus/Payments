package com.quarkus.bootcamp.nttdata.infraestructure.entity.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LineOfCreditD {
  protected Long id;
  /**
   * Monto de la linea de credito registrado al momento de crear el producto
   */
  protected Double amount;
  /**
   * Id del cliente al que le pertenece el producto
   */
  protected Long customerId;
  /**
   * Saldo disponible en la linea de credito
   */
  protected Double available;
  /**
   * Gastos de la linea de credito.
   * Lo consumido.
   */
  protected Double costs;
  /**
   * Fecha de cierre para el calculo del pago (dd-mm)
   */
  protected String closingDate;
  /**
   * Fecha de pago de la cuota (dd-mm).
   */
  protected String paymentDueDate;
}
