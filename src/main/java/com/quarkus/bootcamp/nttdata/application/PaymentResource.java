package com.quarkus.bootcamp.nttdata.application;

import com.quarkus.bootcamp.nttdata.domain.entity.Payment;
import com.quarkus.bootcamp.nttdata.domain.exceptions.AmountExceedsException;
import com.quarkus.bootcamp.nttdata.domain.exceptions.ProductNotFoundException;
import com.quarkus.bootcamp.nttdata.domain.services.PaymentService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {
  @Inject
  PaymentService service;

  @GET
  public Response getAll(@QueryParam("productId") Long productId) {
    List<Payment> paymentList = service.getAll();
    if (productId != null)
      paymentList = paymentList.stream()
            .filter(p -> (p.getProductId() == productId))
            .toList();
    return Response.ok(paymentList).build();
  }

  @POST
  @Transactional
  public Response deposit(Payment payment) {
    try {
      return Response.ok(service.create(payment)).status(201).build();
    } catch (ProductNotFoundException | AmountExceedsException e) {
      return Response.ok(e.getMessage()).status(404).build();
    }
  }
}
