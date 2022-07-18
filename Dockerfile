FROM openjdk:11.0-jdk-slim
COPY . .
EXPOSE 8080
CMD ["java", "-jar","/build/libs/beers-api.jar"]