# Користиме Maven image за билд
FROM maven:3.8.1-openjdk-17-slim AS build

# Работна директорија во контејнерот
WORKDIR /app

# Копирање на сите фајлови во Docker контејнерот
COPY . .

# Извршување на Maven build
RUN mvn clean package -DskipTests

# Финален контејнер за испорачување
FROM openjdk:17-jdk-slim

# Копирање на изградената апликација од build контејнерот
COPY --from=build /app/target/secret-scribe-0.0.1-SNAPSHOT.jar /app/secret-scribe.jar

# Порти што ќе ги користи апликацијата
EXPOSE 8080

# Команда за стартување на апликацијата
ENTRYPOINT ["java", "-jar", "/app/secret-scribe.jar"]
