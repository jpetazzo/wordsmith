FROM maven:3.9.8-eclipse-temurin-17 AS builder

WORKDIR /workspace
# Copier pom.xml d'abord pour bénéficier du cache des dépendances
COPY pom.xml .
# Télécharger les dépendances (cachées si pom.xml n'a pas changé)
RUN mvn -q dependency:resolve dependency:copy-dependencies -DskipTests
# Puis copier le code source
COPY src ./src
# Compiler uniquement avec les sources modifiées
RUN mvn -q package -DskipTests

FROM eclipse-temurin:17.0.11_9-jre-jammy
WORKDIR /app
COPY --from=builder /workspace/target/words.jar .
COPY --from=builder /workspace/target/dependency ./dependency

EXPOSE 8080
CMD ["java", "-Xmx8m", "-Xms8m", "-jar", "words.jar"]
