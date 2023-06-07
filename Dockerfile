# syntax=docker/dockerfile:1

FROM openjdk:17-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} rest-docker.jar


CMD ["java", "-jar","-Dserver.address=0.0.0.0", "-Dserver.port=8080", "rest-docker.jar"]