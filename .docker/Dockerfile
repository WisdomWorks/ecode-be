FROM --platform=amd64 openjdk:17
COPY ../ /workspace
WORKDIR /workspace
RUN chmod +x ./mvnw
# Run the application using maven
CMD ["./mvnw", "spring-boot:run"]