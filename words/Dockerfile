# Builder
FROM maven:3-openjdk-8 AS builder

WORKDIR /build
COPY . .

RUN mvn verify

# Application
FROM openjdk:8

WORKDIR /app
COPY --from=builder /build/target .

EXPOSE 8080
ENTRYPOINT [ "java", "-Xmx8m", "-Xms8m", "-jar", "words.jar"]