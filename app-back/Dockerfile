FROM back-dependencies:latest AS BUILD

WORKDIR /app

ADD src/ src
ADD pom.xml .
RUN ["mvn", "clean", "install"]

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=BUILD /app/target/backend.jar ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9090", "-jar","backend.jar"]
