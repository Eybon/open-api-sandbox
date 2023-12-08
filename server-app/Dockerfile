FROM openjdk:17-alpine as builder
MAINTAINER github.com/Eybon
WORKDIR /tmp
ARG JAR_FILE=java-spring-application-template/target/*.jar
COPY ${JAR_FILE} application.jar
RUN ls -alF
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:17-alpine
MAINTAINER github.com/Eybon
WORKDIR /app
COPY --from=builder /tmp/dependencies/ ./
COPY --from=builder /tmp/spring-boot-loader/ ./
COPY --from=builder /tmp/snapshot-dependencies/ ./
COPY --from=builder /tmp/application/ ./

USER 1001
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 CMD curl -f -s localhost:8080/actuator/health | grep UP || exit 1
