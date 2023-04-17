package com.sebastian_daschner.quarkus;

import io.micrometer.core.annotation.Counted;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class Greeter {

    @ConfigProperty(name = "example.config")
    String config;

    @ConfigProperty(name = "example.list")
    List<String> configs;

    @Counted
    public String greet() {
        System.out.println("configs = " + configs);

        return "Hello " + config;
    }

}
