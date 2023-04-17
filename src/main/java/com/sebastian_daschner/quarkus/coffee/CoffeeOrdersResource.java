package com.sebastian_daschner.quarkus.coffee;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeOrdersResource {

    @Inject
    Jsonb jsonb;

    @GET
    public List<CoffeeOrder> orders() {
        return List.of(new CoffeeOrder("Espresso"), new CoffeeOrder("Cappuccino"));
    }

    @GET
    @Path("123")
    public CoffeeOrder order() {
        CoffeeOrder order = new CoffeeOrder("Espresso");

        String json = jsonb.toJson(order);
        System.out.println("json = " + json);

        return order;
    }

    @POST
    public Response create(CoffeeOrder order) {
        System.out.println("order " + order + " created");

        return Response.accepted().build();
    }

}
