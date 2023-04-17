package com.sebastian_daschner.quarkus.coffee;

import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import java.util.concurrent.locks.LockSupport;

public class OrderProcessor {

    public void onCreatedOrder(@ObservesAsync CoffeeOrder order) {
        LockSupport.parkNanos(2_000_000_000L);
        System.out.println("order processed: " + order);
    }

}
