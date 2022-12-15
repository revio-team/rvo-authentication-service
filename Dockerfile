FROM openjdk:8-jdk-alpine
RUN addgroup -S revio && adduser -S revio -G revio
USER revio:revio

VOLUME /tmp

ARG JWT_SECRET
ARG MONGO_URI

ENV JWT_SECRET=$JWT_SECRET
ENV MONGO_URI=$MONGO_URI

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]