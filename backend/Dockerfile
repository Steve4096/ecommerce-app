## Use Maven image to build the app
#FROM maven:3.9.4-eclipse-temurin-17 AS build
#
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests
#
## Use a JDK runtime to run the app
#FROM eclipse-temurin:17-jdk
#WORKDIR /app
#COPY --from=build /app/target/*.jar app.jar
#
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]

# 1. Build Stage
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# 2. Runtime Stage
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
