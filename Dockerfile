FROM maven:3.8.4-openjdk-17

WORKDIR /app
COPY pom.xml pom.xml
COPY src src

CMD ["mvn", "test"]
