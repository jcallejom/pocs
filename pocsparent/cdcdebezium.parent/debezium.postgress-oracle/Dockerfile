FROM maven:3.8.5-openjdk-17 as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:17.0.2-jdk-slim

COPY --from=builder /usr/src/app/target/debezium.postgress-oracle-1.0.0-SNAPSHOT.jar /app.jar

ENTRYPOINT java -jar app.jar 