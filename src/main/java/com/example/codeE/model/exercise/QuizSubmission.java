package com.example.codeE.model.exercise;

import com.example.codeE.model.exercise.common.QuizAnswers;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "QuizSubmission")
public class QuizSubmission extends Submission {
    @Field
    @NotNull(message = "Submission is required")
    private List<QuizAnswers> submission;

    public QuizSubmission(String studentId, String exerciseId, float score, boolean reviewable, List<QuizAnswers> submission) {
        super(studentId, exerciseId, score, reviewable);
        this.submission = submission;
    }

    public QuizSubmission(String studentId, String exerciseId, boolean reviewable, List<QuizAnswers> submission) {
        super(studentId, exerciseId, reviewable);
        this.submission = submission;
    }

    @Override
    public String toString() {
        return "QuizSubmission{" +
                "submission=" + submission +
                '}';
    }
}
