FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY . /build/
WORKDIR /build/
RUN mvn -DskipTests package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/countme-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "countme-0.0.1-SNAPSHOT.jar"]