package com.quarkus.bootcamp.nttdata.infraestructure.resources;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.product.CreditD;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/credits")
public interface IcreditApi {

  @GET
  @Path("/")
  List<CreditD> getAll();

  @GET
  @Path("/{id}")
  @Fallback(fallbackMethod = "fallbackGetById")
  CreditD getById(@PathParam("id") Long id);

  default CreditD fallbackGetById(Long id) {
    return new CreditD();
  }

  @PUT
  @Path("/{id}")
  CreditD update(@PathParam("id") Long id, CreditD creditD);
}
