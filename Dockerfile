FROM openjdk:8-jdk-alpine

RUN apk add --no-cache putty
RUN apk add --no-cache bash
RUN apk add --no-cache curl


ADD target/flag-picker-1.0-SNAPSHOT.jar /flag-picker.jar
CMD /usr/bin/java -jar /flag-picker.jar

EXPOSE 8080