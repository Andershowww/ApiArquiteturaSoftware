# ====== build ======
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests clean package

# ====== runtime ======
FROM eclipse-temurin:17-jdk
WORKDIR /app
# copia qualquer jar gerado pelo build (ex.: api-1.0.0.jar)
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
