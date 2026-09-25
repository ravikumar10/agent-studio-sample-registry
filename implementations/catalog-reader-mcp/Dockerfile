FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src src
RUN mvn -B package -DskipTests
FROM eclipse-temurin:21-jre
RUN useradd --system --uid 10001 app
COPY --from=build /workspace/target/catalog-reader-mcp-*.jar /app.jar
USER app
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
