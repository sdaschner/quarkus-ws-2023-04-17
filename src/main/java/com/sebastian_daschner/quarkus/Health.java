package com.sebastian_daschner.quarkus;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@Readiness
@ApplicationScoped
public class Health implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
//        if (new Random().nextBoolean())
//            return HealthCheckResponse.down("example-app");
        return HealthCheckResponse.up("example-app");
    }

}
