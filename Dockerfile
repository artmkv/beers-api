FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon


FROM openjdk:11.0-jdk-slim
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/

EXPOSE 8092
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/beers-api-0.0.1-SNAPSHOT.jar"]

#CMD ["java", "-jar","/build/libs/beers-api-0.0.1-SNAPSHOT.jar"]