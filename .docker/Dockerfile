FROM maven:3.8.4-openjdk-21-slim as build
VOLUME /tmp
COPY . /app
WORKDIR /app
RUN mvn clean package

FROM eclipse-temurin:21-jdk-alpine as runner
VOLUME /tmp
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]