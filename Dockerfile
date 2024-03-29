FROM openjdk:17-oracle
LABEL maintainer = trainee
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY $JAR_FILE employeerest.jar
ENTRYPOINT ["java","-jar","employeerest.jar"]