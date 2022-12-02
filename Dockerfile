FROM openjdk:8-jdk-alpine
RUN addgroup -S revio && adduser -S revio -G revio
USER revio:revio

ARG LOCATION_JAR_FILE=build/libs/*.jar
RUN echo $LOCATION_JAR_FILE
ARG CONTAINER_JAR_FILE=app.jar


COPY ${LOCATION_JAR_FILE} $CONTAINER_JAR_FILE

EXPOSE 8080


ENTRYPOINT ["java","-jar","app.jar"]