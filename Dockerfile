# syntax=docker/dockerfile:1

FROM openjdk:17-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} rest-docker.jar


CMD ["java", "-jar", "rest-docker.jar"]