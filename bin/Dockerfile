# Start with a base image containing Java runtime
FROM openjdk:8u212-jdk-alpine

# Make port 8591 available to the world outside this container
EXPOSE 8591

# The application's jar file
ARG JAR_FILE=target/trainig.jar
ENV SB_PROFILE $SB_PROFILE

# Add the application's jar to the container
ADD ${JAR_FILE} base.jar

# Run the jar file
ENTRYPOINT java -Xms128m -Xmx512m -XX:MaxMetaspaceSize=1024m -Dspring.profiles.active=$SB_PROFILE -jar /base.jar
