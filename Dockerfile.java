FROM openjdk:11

WORKDIR /app

COPY ./template/java/src ./student-submission

WORKDIR /app/student-submission

RUN javac ./Main.java
RUN javac ./Calculator.java

CMD ["java", "Main"]
