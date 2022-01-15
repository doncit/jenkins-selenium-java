FROM openjdk:17.0.1-jdk
WORKDIR /
COPY target/JenkinsSeleniumJava-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
CMD ["java", "-jar", "app.jar"]
