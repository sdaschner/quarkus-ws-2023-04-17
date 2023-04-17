package com.sebastian_daschner.quarkus.coffee;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "coffee_orders")
public class CoffeeOrder extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @JsonbTransient
    public UUID id;

    @NotNull
    public String type;

    public CoffeeOrder() {
    }

    public CoffeeOrder(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
               "id=" + id +
               ", type='" + type + '\'' +
               '}';
    }

    public static List<CoffeeOrder> listAllCappuccinos() {
        return list("type", "Cappuccino");
    }

}
