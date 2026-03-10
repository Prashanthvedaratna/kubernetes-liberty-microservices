# Stage 1: Build the application
FROM maven:3.8-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Stage 2: Run with Liberty
FROM icr.io/appcafe/open-liberty:kernel-slim-java11-openj9-ubi
COPY --from=builder /app/target/product-service.war /config/apps/
COPY --from=builder /app/src/main/liberty/config/server.xml /config/

# Configure Liberty
RUN configure.sh

# Set Kubernetes readiness/liveness endpoints
EXPOSE 9080 9443