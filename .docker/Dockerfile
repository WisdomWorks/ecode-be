FROM --platform=amd64 openjdk:17

ARG TAG=0.0.2
ARG GITHUB_TOKEN

WORKDIR /app

# Download the JAR file from GitHub Packages
RUN curl -L -H "Authorization: Bearer $GITHUB_TOKEN" -o codee.jar \
    "https://maven.pkg.github.com/WisdomWorks/ecode-be/com/example/codee/$TAG/codee-$TAG.jar"

# Run the application
CMD ["java", "-jar", "codee.jar"]