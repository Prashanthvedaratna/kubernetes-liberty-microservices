# Stage 1: Build the application
FROM maven:3.8-openjdk-11 AS builder
WORKDIR /app

# Copy parent POM
COPY pom.xml .

# Copy ALL module directories (to satisfy parent POM module list)
COPY product-service product-service
COPY order-service order-service
COPY user-service user-service

# Build only product-service (and its dependencies, i.e., the parent)
RUN mvn -pl product-service -am clean package

# Stage 2: Run with Liberty
#ROM icr.io/appcafe/open-liberty:kernel-slim-java11-openj9-ubi
#FROM icr.io/appcafe/open-liberty:microProfile4-java11-openj9-ubi
FROM openliberty/open-liberty:full-java11-openj9-ubi
COPY --from=builder /app/product-service/target/product-service.war /config/apps/
COPY product-service/src/main/liberty/config/server.xml /config/
#RUN configure.sh
EXPOSE 9080 9443
