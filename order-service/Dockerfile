FROM maven:3.8-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
COPY product-service product-service
COPY order-service order-service
COPY user-service user-service
RUN mvn -pl order-service -am clean package

#FROM icr.io/appcafe/open-liberty:kernel-slim-java11-openj9-ubi
#FROM icr.io/appcafe/open-liberty:microProfile4-java11-openj9-ubi
FROM openliberty/open-liberty:full-java11-openj9-ubi
COPY --from=builder /app/order-service/target/order-service.war /config/apps/
COPY order-service/src/main/liberty/config/server.xml /config/
#RUN configure.sh
EXPOSE 9080 9443
