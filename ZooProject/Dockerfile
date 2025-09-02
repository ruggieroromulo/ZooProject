FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/zoo-project.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]