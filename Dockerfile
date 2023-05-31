# Start with a base image
# FROM openjdk:17-jdk-slim
FROM openjdk:17-alpine

# Set the working directory
# WORKDIR /app

# Copy the Spring Boot jar file to the container
COPY ./server/build/libs/server-0.0.1.jar app.jar

# Expose the container port
EXPOSE 8080

# Define the command to run when the container starts
CMD ["java", "-jar", "app.jar"]