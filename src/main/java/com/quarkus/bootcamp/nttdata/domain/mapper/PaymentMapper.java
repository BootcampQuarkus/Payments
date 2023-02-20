package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.Payment;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.payments.PaymentD;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentMapper implements IMapper<Payment, PaymentD> {
  @Override
  public Payment toDto(PaymentD paymentD) {
    Payment payment = new Payment();
    payment.setId(paymentD.id);
    payment.setAmount(paymentD.getAmount());
    payment.setDescription(paymentD.getDescription());
    payment.setProductId(paymentD.getProductId());
    payment.setDate(paymentD.getCreatedAt());
    return payment;
  }

  @Override
  public PaymentD toEntity(Payment payment) {
    PaymentD paymentD = new PaymentD();
    paymentD.setAmount(payment.getAmount());
    paymentD.setDescription(payment.getDescription());
    paymentD.setProductId(payment.getProductId());
    return paymentD;
  }
}
