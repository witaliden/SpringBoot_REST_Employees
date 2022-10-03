FROM openjdk:17-oracle
LABEL maintainer = trainee
#CMD mvn clean install
#WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY $JAR_FILE employeerest.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","employeerest.jar"]