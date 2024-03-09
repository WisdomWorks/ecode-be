package com.example.codeE.model.exercise.common;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "QuizAnswers")
public class QuizAnswers {
    @Id
    private String quizAnswerId;

    @Field
    @NotNull(message = "Question ID is required")
    private String questionId;

    @Field
    @NotNull(message = "Answer list is required")
    private List<QuizChoice> answers;

    public QuizAnswers(String questionId, List<QuizChoice> answers) {
        this.questionId = questionId;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuizAnswers{" +
                "quizAnswerId='" + quizAnswerId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answers=" + answers +
                '}';
    }
}
