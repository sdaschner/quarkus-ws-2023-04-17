package com.sebastian_daschner.quarkus.coffee;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("index.html")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class OrderController {

    @Location("index.html")
    Template template;

    @Inject
    CoffeeShop coffeeShop;

    @GET
    public TemplateInstance index() {
        List<CoffeeOrder> orders = coffeeShop.getOrders();

        return template.data("orders", orders);
    }

}
