package com.example.codeE.model.exercise;

import com.example.codeE.model.exercise.common.QuizAnswers;
import com.example.codeE.request.exercise.quiz.CreateQuizSubmissionRequest;
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

    public QuizSubmission(String studentId, String exerciseId, float score, boolean reviewable, List<QuizAnswers> submission, String teacherComment) {
        super(studentId, exerciseId, score, reviewable, teacherComment);
        this.submission = submission;
    }

    public QuizSubmission(String studentId, String exerciseId, boolean reviewable, List<QuizAnswers> submission, String teacherComment) {
        super(studentId, exerciseId, reviewable, teacherComment);
        this.submission = submission;
    }
    public QuizSubmission(CreateQuizSubmissionRequest request, float score){
        super(request.getStudentId(), request.getExerciseId(), score, true, request.getTeacherComment());
        this.submission = request.getSubmission();
    }
    @Override
    public String toString() {
        return "QuizSubmission{" +
                "submission=" + submission +
                '}';
    }
}
