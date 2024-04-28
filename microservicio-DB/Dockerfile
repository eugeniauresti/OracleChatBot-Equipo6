FROM openjdk:22 as build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN ./mvnw package -DskipTests

FROM openjdk:22
WORKDIR /app
COPY wallet /app/wallet
COPY --from=build /app/target/Microservice_OAD-0.0.1-SNAPSHOT.jar /app/microservicio_database.jar
ENTRYPOINT ["java", "-jar", "microservicio_database.jar"]
