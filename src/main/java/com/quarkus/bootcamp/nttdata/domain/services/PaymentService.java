package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.entity.Payment;
import com.quarkus.bootcamp.nttdata.domain.exceptions.AmountExceedsException;
import com.quarkus.bootcamp.nttdata.domain.exceptions.ProductNotFoundException;
import com.quarkus.bootcamp.nttdata.domain.mapper.PaymentMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.payments.PaymentD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.product.CreditD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.product.LineOfCreditD;
import com.quarkus.bootcamp.nttdata.infraestructure.repository.PaymentRepository;
import com.quarkus.bootcamp.nttdata.infraestructure.resources.ILineOfCreditApi;
import com.quarkus.bootcamp.nttdata.infraestructure.resources.IcreditApi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class PaymentService {
  @Inject
  PaymentRepository repository;
  @Inject
  PaymentMapper mapper;
  @RestClient
  IcreditApi creditApi;
  @RestClient
  ILineOfCreditApi lineOfCreditApi;

  public List<Payment> getAll() {
    return repository.getAll()
          .stream()
          .filter(p -> (p.getDeletedAt() == null))
          .map(mapper::toDto)
          .toList();
  }

  public Payment create(Payment payment) throws ProductNotFoundException, AmountExceedsException {
    PaymentD paymentD = mapper.toEntity(payment);
    Long productId = payment.getProductId();
    Double amount = payment.getAmount();
    switch (this.validateProduct(productId)) {
      case 1:
        CreditD creditD = creditApi.getById(productId);
        if (amount > creditD.getBalance()) {
          throw new AmountExceedsException("Amount Exceeds balance");
        }
        paymentD = repository.save(paymentD);
        if (amount.equals(creditD.getBalance())) {
          creditD.setDues(0);
        } else {
          creditD.setDues(creditD.getDues() - 1);
        }
        creditD.setBalance(creditD.getBalance() - amount);
        creditApi.update(productId, creditD);
        break;
      case 2:
        LineOfCreditD lineOfCreditD = lineOfCreditApi.getById(productId);
        if (amount > lineOfCreditD.getCosts()) {
          throw new AmountExceedsException("Amount Exceeds costs");
        }
        paymentD = repository.save(paymentD);
        lineOfCreditD.setAvailable(lineOfCreditD.getAvailable() + amount);
        lineOfCreditD.setCosts(lineOfCreditD.getCosts() - amount);
        lineOfCreditApi.update(productId, lineOfCreditD);
        break;
    }
    return mapper.toDto(paymentD);
  }

  public Integer validateProduct(Long productId) throws ProductNotFoundException {
    CreditD creditD = creditApi.getById(productId);
    if (creditD.getId() != null) return 1;
    LineOfCreditD lineOfCreditD = lineOfCreditApi.getById(productId);
    if (lineOfCreditD.getId() != null) return 2;
    throw new ProductNotFoundException("Product not found.");
  }
}
