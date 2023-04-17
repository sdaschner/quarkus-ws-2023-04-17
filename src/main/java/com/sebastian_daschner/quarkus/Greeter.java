package com.sebastian_daschner.quarkus;

import io.quarkus.arc.Lock;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;

@ApplicationScoped
//@Dependent
public class Greeter {

    public Greeter() {
        System.out.println("Greeter.Greeter");
    }

//    @Lock(value = Lock.Type.WRITE)
    public String greet() {
        return "Hello World";
    }

}
