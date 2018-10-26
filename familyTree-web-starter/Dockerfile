#ava:8u111-jdk-alpine java:8-jdk-alpine
FROM java
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
RUN sh -c 'touch /app.jar'

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
