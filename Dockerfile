FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY ./target/mymavenapp-*.jar /usr/app/
WORKDIR /usr/app

CMD java -jar mymavenapp-*.jar


