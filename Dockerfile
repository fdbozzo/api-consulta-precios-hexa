FROM docker.io/library/eclipse-temurin:17-jre
VOLUME /tmp
ARG JAR_FILE=target/pricing-api*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#CMD ["tail", "-f", "/dev/null"]