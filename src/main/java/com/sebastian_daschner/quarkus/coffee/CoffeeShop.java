package com.sebastian_daschner.quarkus.coffee;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CoffeeShop {

    @Inject
    Event<CoffeeOrder> createdOrders;

//    @Inject
//    CoffeeOrderRepository repository;

    public List<CoffeeOrder> getOrders() {
        return CoffeeOrder.listAll();
//        return repository.listAll();
    }

    public CoffeeOrder getOrder(UUID id) {
        return CoffeeOrder.findById(id);
//        return repository.findById(id);
    }

    public List<CoffeeOrder> getCappuccinoOrders() {
//        return repository.listAllCappuccinos();
        return CoffeeOrder.listAllCappuccinos();
    }

    @Transactional
    public void createOrder(CoffeeOrder order) {
//        repository.persist(order);
        order.persist();

        System.out.println("order " + order + " created");

        createdOrders.fireAsync(order);
    }

}
