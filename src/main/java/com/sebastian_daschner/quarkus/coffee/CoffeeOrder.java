package com.sebastian_daschner.quarkus.coffee;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "coffee_orders")
public class CoffeeOrder {

    @Id
    @GeneratedValue
    @JsonbTransient
    private UUID id;

    @NotNull
    private String type;

    public CoffeeOrder() {
    }

    public CoffeeOrder(String type) {
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
               "id=" + id +
               ", type='" + type + '\'' +
               '}';
    }
}
