FROM openjdk:11.0-jdk-slim
COPY . .
EXPOSE 8092
CMD ["java", "-jar","/build/libs/beers-api-0.0.1-SNAPSHOT.jar"]