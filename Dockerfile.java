FROM openjdk:11

WORKDIR /app

COPY ./template/java/src ./student-submission

WORKDIR /app/student-submission

CMD ["tail", "-f", "/dev/null"]
