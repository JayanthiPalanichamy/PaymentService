FROM openjdk:17.0.2-jdk
WORKDIR /opt
ENV PORT 8081
EXPOSE 8081
COPY build/libs/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar