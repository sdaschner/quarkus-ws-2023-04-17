package com.sebastian_daschner.quarkus.coffee;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonCollectors;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeOrdersResource {

    @Inject
    Event<CoffeeOrder> createdOrders;

    @GET
//    public List<CoffeeOrder> orders() {
    public JsonArray orders() {
        List<CoffeeOrder> orders = List.of(new CoffeeOrder("Espresso"), new CoffeeOrder("Cappuccino"));

        return orders.stream()
                .map(o -> Json.createObjectBuilder()
                        .add("type", o.getType()).build())
                .collect(JsonCollectors.toJsonArray());
    }

    @GET
    @Path("date")
    public CoffeeOrderDate orderDate() {
        CoffeeOrder order = new CoffeeOrder("Espresso");
        return new CoffeeOrderDate(order.getType(), LocalDate.now());
    }

    @GET
    @Path("123")
//    public CoffeeOrder order() {
    public JsonObject order() {
        CoffeeOrder order = new CoffeeOrder("Espresso");
        return Json.createObjectBuilder()
                .add("type", order.getType())
//                .add("hello", "World")
//                .add("int", 123)
                .build();
    }

    @POST
    public Response create(@Valid @NotNull CoffeeOrder order) {
        System.out.println("order " + order + " created");

        createdOrders.fireAsync(order);

        return Response.accepted().build();
    }

    public record CoffeeOrderDate(String type, LocalDate date) {
    }

}
