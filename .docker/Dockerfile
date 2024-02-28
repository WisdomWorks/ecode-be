FROM --platform=amd64 openjdk:17
COPY ../ /workspace
WORKDIR /workspace
# Run the application using maven
CMD ["./mvnw", "spring-boot:run"]