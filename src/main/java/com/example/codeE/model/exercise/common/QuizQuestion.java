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
@Document(collection = "QuizQuestion")
public class QuizQuestion {
    @Id
    private String questionId;

    @Field
    @NotNull(message = "Question title is required")
    private String title;

    @Field
    @NotNull(message = "Question description is required")
    private String description;

    @Field
    @NotNull(message = "Question's choices is required")
    private List<QuizChoice> choices;

    @Field
    @NotNull(message = "Question's answers is required")
    private List<QuizChoice> answers;

    public QuizQuestion(String title, String description, List<QuizChoice> choices, List<QuizChoice> answers) {
        this.title = title;
        this.description = description;
        this.choices = choices;
        this.answers = answers;
    }
}
