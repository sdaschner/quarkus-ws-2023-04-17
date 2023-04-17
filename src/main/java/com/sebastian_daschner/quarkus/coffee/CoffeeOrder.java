package com.sebastian_daschner.quarkus.coffee;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CoffeeOrder {

    @JsonbTransient
    public UUID id;

    //    @JsonbProperty("coffeeType")
    @NotNull
    private String type;

    public CoffeeOrder() {
        id = UUID.randomUUID();
    }

    public CoffeeOrder(String type) {
        id = UUID.randomUUID();
        this.type = type;
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
