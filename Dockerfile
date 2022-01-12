FROM adoptopenjdk/maven-openjdk11  AS BUILD
WORKDIR /build
COPY . .
RUN mvn clean package


FROM openjdk:8-jre-alpine
MAINTAINER info@brainupgrade.in
COPY --from=build /build/target/*.jar app.jar
ENTRYPOINT ["/usr/bin/java", "-Djava.security.egd=file:/dev/./urandom", "-jar","app.jar"]

# docker build -t localhost:32000/brainupgrade/global-bank-transaction:1.0.0 .