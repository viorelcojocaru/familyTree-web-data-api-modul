FROM java:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
RUN sh -c 'touch /app.jar'
RUN sh -c 'apk add --no-cache bash'
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
