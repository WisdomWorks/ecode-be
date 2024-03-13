package com.example.codeE.model.exercise;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "EssaySubmission")
public class EssaySubmission extends Submission {
    @Field
    @NotNull(message = "Submission is required")
    private String submission;

    public EssaySubmission(String studentId, String exerciseId, boolean reviewable, String submission) {
        super(studentId, exerciseId, reviewable);
        this.submission = submission;
    }

    public EssaySubmission(String studentId, String exerciseId, float score, boolean reviewable, String submission) {
        super(studentId, exerciseId, score, reviewable);
        this.submission = submission;
    }

    @Override
    public String toString() {
        return "EssaySubmission{" +
                "submission='" + submission + '\'' +
                '}';
    }
}
