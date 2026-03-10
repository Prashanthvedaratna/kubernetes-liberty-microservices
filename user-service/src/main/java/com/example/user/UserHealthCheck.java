package com.example.user;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;
import javax.enterprise.context.ApplicationScoped;

@Liveness
@Readiness
@ApplicationScoped
public class UserHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("user-service")
                .up()
                .withData("service", "user-service")
                .build();
    }
}