# Set the base image for the app
FROM amazoncorretto:17.0.10-alpine

# Set the working directory
WORKDIR /usr/src/app

# Create a user to run app
RUN addgroup -S app && adduser -S appuser -G app

# Set user to appuser
USER appuser

# Copy the jar file into the docker image
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]