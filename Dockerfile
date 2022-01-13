FROM openjdk:8-jre-alpine
MAINTAINER info@brainupgrade.in
COPY target/*.jar app.jar
ENTRYPOINT ["/usr/bin/java", "-Djava.security.egd=file:/dev/./urandom", "-jar","app.jar"]

# mvn clean package && docker build -f Dockerfile -t localhost:32000/brainupgrade/global-bank-transaction:1.0.0 -t brainupgrade/global-bank-transaction:1.0.0 .  && docker push localhost:32000/brainupgrade/global-bank-transaction:1.0.0 && docker push brainupgrade/global-bank-transaction:1.0.0 