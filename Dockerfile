# Use official OpenJDK 21 runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Install necessary dependencies for Maven
RUN apt-get update && apt-get install -y wget unzip

# Download and install Maven (latest version)
RUN wget https://dlcdn.apache.org/maven/maven-3/3.9.0/binaries/apache-maven-3.9.0-bin.tar.gz -O /tmp/maven.tar.gz
RUN mkdir /opt/maven && tar -xvzf /tmp/maven.tar.gz -C /opt/maven
RUN ln -s /opt/maven/apache-maven-3.9.0/bin/mvn /usr/local/bin/mvn

# Verify Maven installation
RUN mvn -v

# Copy the jar file into the container
COPY target/my-app.jar /app/my-app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "my-app.jar"]
