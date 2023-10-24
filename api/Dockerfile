# Build stage for Maven
FROM maven:3.8.4-openjdk-17-slim AS maven_builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package

# Final stage for the application
FROM eclipse-temurin:17.0.8.1_1-jre-alpine
USER root

# Copy the JAR file built by Maven
COPY --from=maven_builder /app/target/*.jar application.jar

# Expose port on which Spring Boot app will run
EXPOSE 8080

# Switch to non-root user
USER 1001

# Start Spring Boot app
ENTRYPOINT ["java", "-jar", "application.jar"]