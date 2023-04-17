package com.sebastian_daschner.quarkus;

import io.quarkus.scheduler.Scheduled;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("q") @DefaultValue("") String q) {
        System.out.println("hello() executed: " + q);
        return "Hello World?";
    }

    @Scheduled(every = "3s")
    public void invoke() {
        System.out.println("GreetingResource.invoke");
    }
}