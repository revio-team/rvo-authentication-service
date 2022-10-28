FROM openjdk:8-jdk-alpine
RUN addgroup -S revio && adduser -S revio -G revio
USER revio:revio
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]