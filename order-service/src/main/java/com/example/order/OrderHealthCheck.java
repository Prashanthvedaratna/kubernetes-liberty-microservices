package com.example.order;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;
import javax.enterprise.context.ApplicationScoped;

@Liveness
@Readiness
@ApplicationScoped
public class OrderHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("order-service")
                .up()
                .withData("service", "order-service")
                .build();
    }
}