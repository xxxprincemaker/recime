# Build Phase
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
WORKDIR /app

# copy only pom.xml and download dependencies, to cache dependencies it will only change if pom.xml changes
COPY pom.xml /app/
RUN mvn dependency:go-offline -B

# copy the rest of the app and build it.
COPY src /app/src
RUN mvn clean package

# Running Phase
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
