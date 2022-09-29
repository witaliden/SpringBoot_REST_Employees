FROM openjdk:17-oracle
MAINTAINER trainee.com
ARG JAR_FILE=target/*.jar
COPY $JAR_FILE Docker-RESTapp.jar
ENTRYPOINT ["java","-jar","/Docker-RESTapp.jar"]