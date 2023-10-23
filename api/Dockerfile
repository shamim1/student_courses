FROM eclipse-temurin:17.0.8.1_1-jdk-alpine as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:17.0.8.1_1-jre-alpine
USER root
#Copy individual layers one by one
COPY --from=builder dependencies/ ./
#Add this to fix a bug which happens during sequential copy commands
RUN true
COPY --from=builder snapshot-dependencies/ ./
RUN true
COPY --from=builder spring-boot-loader/ ./
RUN true
COPY --from=builder application/ ./
#Expose port on which Spring Boot app will run
EXPOSE 8080
#Switch to non root user`
USER 1001
#Start Spring Boot app
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]