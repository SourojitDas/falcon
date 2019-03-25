# Alpine Linux with JDK 8 JRE
FROM java:8-jre-alpine
# copy JAR into image
COPY build/libs/falconserver-1.0-SNAPSHOT.jar /app.jar 
# run application with this command line 
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app.jar"]
