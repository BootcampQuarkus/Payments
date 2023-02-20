package com.quarkus.bootcamp.nttdata.infraestructure.repository;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.payments.PaymentD;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PaymentRepository implements IRepository<PaymentD> {
  @Override
  public List<PaymentD> getAll() {
    return PaymentD.listAll(Sort.by("id"));
  }

  @Override
  public PaymentD getById(Long id) {
    Optional<PaymentD> paymentD = PaymentD.findByIdOptional(id);
    if (paymentD.isEmpty()) {
      throw new NullPointerException("Account not found");
    }
    return paymentD.get();
  }

  @Override
  public PaymentD save(PaymentD paymentD) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu.MM.dd.HH:mm:ss");
    String date = ZonedDateTime.now(ZoneId.systemDefault()).format(formatter);
    if (paymentD.getCreatedAt() == null) {
      paymentD.setCreatedAt(date);
    } else {
      paymentD.setUpdatedAt(date);
    }
    paymentD.persist();
    return paymentD;
  }

  @Override
  public PaymentD softDelete(PaymentD paymentD) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu.MM.dd.HH:mm:ss");
    paymentD.setDeletedAt(ZonedDateTime.now(ZoneId.systemDefault()).format(formatter));
    return this.save(paymentD);
  }
}
