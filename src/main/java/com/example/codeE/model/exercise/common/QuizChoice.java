package com.example.codeE.model.exercise.common;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "QuizChoice")
public class QuizChoice {
    @Id
    private String choiceId;

    @Field
    @NotNull(message = "Quiz choice content is required")
    private String content;

    public QuizChoice(String content) {
        this.content = content;
    }
}
