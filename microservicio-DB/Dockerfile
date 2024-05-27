FROM openjdk:11
WORKDIR /app
EXPOSE 8080
COPY wallet /app/wallet
COPY target/Microservice_OAD-0.0.1-SNAPSHOT.jar microservicio_database.jar
ENTRYPOINT ["java", "-jar", "microservicio_database.jar"]
