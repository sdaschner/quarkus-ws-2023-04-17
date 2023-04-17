package com.sebastian_daschner.quarkus.coffee;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonCollectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeOrdersResource {

    @Inject
    CoffeeShop coffeeShop;

    @GET
//    public List<CoffeeOrder> orders() {
    public JsonArray orders() {
        List<CoffeeOrder> orders = coffeeShop.getOrders();

        return orders.stream()
                .map(o -> Json.createObjectBuilder()
                        .add("type", o.getType()).build())
                .collect(JsonCollectors.toJsonArray());
    }

    @GET
    @Path("cappuccinos")
    public List<CoffeeOrder> cappuccinos() {
        return coffeeShop.getCappuccinoOrders();
    }

    @GET
    @Path("{id}")
    public CoffeeOrder order(@PathParam("id") UUID id) {
        return coffeeShop.getOrder(id);
    }

    @POST
    public Response create(@Valid @NotNull CoffeeOrder order) {
        coffeeShop.createOrder(order);
        return Response.accepted().build();
    }


    public record CoffeeOrderDate(String type, LocalDate date) {
    }

}
