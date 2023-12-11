# Base image to build application and JRE
FROM maven:3.9.5-amazoncorretto-11 AS build-image

WORKDIR /app

# Build application
COPY ./ ./

# This requires docker:buildkit enabled
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package
RUN cp ./target/arkenstone-transactions-service.jar ./arkenstone-transactions-service.jar

# Base image to build a JRE
FROM amazoncorretto:11-alpine as corretto-jdk

# required for strip-debug to work
RUN apk add --no-cache binutils

# Build small JRE image
RUN jlink \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime

# Main app image
FROM alpine:latest

ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=corretto-jdk /javaruntime $JAVA_HOME

RUN mkdir /app
RUN addgroup -S javauser && adduser -S javauser -s /bin/false -G javauser

COPY --from=build-image /app/arkenstone-transactions-service.jar /app/arkenstone-transactions-service.jar
RUN chown -R javauser:javauser /app
USER javauser
WORKDIR /app

CMD java -jar arkenstone-transactions-service.jar --spring.profiles.active=docker