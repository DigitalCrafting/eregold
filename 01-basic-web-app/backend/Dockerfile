# Base image to build application and JRE
FROM maven:3.9.5-amazoncorretto-11 AS build-image

WORKDIR /app

# Build application
ADD . /app

# This requires docker:buildkit enabled
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean install
RUN cp ./api/target/eregold-app.jar ./eregold-app.jar

# Main app image
FROM openjdk:11-jre-slim

RUN mkdir /app \
    && groupadd --system --gid 3000 javauser \
    && useradd --system --uid 1000 --shell /bin/false --gid javauser javauser
COPY --from=build-image /app/eregold-app.jar /app/eregold-app.jar
RUN chown -R javauser:javauser /app
USER javauser
WORKDIR /app

CMD java -jar eregold-app.jar --spring.profiles.active=docker