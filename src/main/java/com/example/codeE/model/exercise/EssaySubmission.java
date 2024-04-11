package com.example.codeE.model.exercise;

import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
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

    public EssaySubmission(String studentId, String exerciseId, boolean reviewable, String submission, String teacherComment) {
        super(studentId, exerciseId, reviewable, teacherComment);
        this.submission = submission;
    }

    public EssaySubmission(String studentId, String exerciseId, float score, boolean reviewable, String submission, String teacherComment) {
        super(studentId, exerciseId, score, reviewable, teacherComment);
        this.submission = submission;
    }
    public EssaySubmission(CreateEssaySubmissionRequest request, float score){
        super(request.getStudentId(), request.getExerciseId(), score, true, "");
        this.submission = request.getSubmission();
    }
    @Override
    public String toString() {
        return "EssaySubmission{" +
                "submission='" + submission + '\'' +
                '}';
    }
}
