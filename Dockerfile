# ---------- BUILD ----------
FROM gradle:8.7-jdk21 AS builder

WORKDIR /app
COPY . .

RUN gradle clean bootJar --no-daemon

# ---------- RUNTIME ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
