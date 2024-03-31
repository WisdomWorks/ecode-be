package com.example.codeE.request.exercise.quiz;

import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.model.exercise.common.QuizQuestion;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestionResponse {
    private String questionId;
    private String title;
    private String description;
    private List<QuizChoice> choices;
    public QuizQuestionResponse(QuizQuestion question){
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.description = question.getDescription();
        this.choices = question.getChoices();
    }
}
