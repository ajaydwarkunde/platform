# ---------- BUILD STAGE ----------
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

COPY settings.gradle build.gradle gradlew gradlew.bat /app/
COPY gradle /app/gradle

COPY notification-service /app/notification-service
COPY common-events /app/common-events

RUN ./gradlew :notification-service:bootJar --no-daemon


# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/notification-service/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
