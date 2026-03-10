FROM maven:3.8-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM icr.io/appcafe/open-liberty:kernel-slim-java11-openj9-ubi
COPY --from=builder /app/target/order-service.war /config/apps/
COPY --from=builder /app/src/main/liberty/config/server.xml /config/
RUN configure.sh
EXPOSE 9080 9443