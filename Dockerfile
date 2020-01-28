FROM openjdk:8-jdk-alpine

#EXPOSED PORT
EXPOSE 8080

RUN mkdir /log

ADD target/protein-gamification.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
