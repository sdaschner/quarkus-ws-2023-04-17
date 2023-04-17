package com.sebastian_daschner.quarkus.coffee;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CoffeeOrderRepository implements PanacheRepositoryBase<CoffeeOrder, UUID> {

    public List<CoffeeOrder> listAllCappuccinos() {
        return list("type", "Cappuccino");
    }

}
