FROM openjdk:17.0.2-jdk
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY build/libs/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar