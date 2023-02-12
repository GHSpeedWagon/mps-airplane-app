FROM openjdk:17-oracle
ADD target/mps-app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]