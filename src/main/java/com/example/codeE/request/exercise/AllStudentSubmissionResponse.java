package com.example.codeE.request.exercise;

import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.model.exercise.QuizSubmission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllStudentSubmissionResponse {
    private String submissionId;
    private String exerciseId;
    private String exerciseTitle;
    private String studentId;
    private String dateGrade;
    private String dateSubmission;
    private String type;
    private Float score;

    public AllStudentSubmissionResponse(QuizSubmission quiz, String title){
        this.submissionId = quiz.getSubmissionId();
        this.exerciseId = quiz.getExerciseId();
        this.exerciseTitle = title;
        this.studentId = quiz.getStudentId();
        this.dateGrade = quiz.getDateGrade();
        this.dateSubmission = quiz.getDateSubmit();
        this.type = "quiz";
        this.score = quiz.getScore();
    }
    public AllStudentSubmissionResponse(CodeSubmission code, String title){
        this.submissionId = code.getSubmissionId();
        this.exerciseId = code.getExerciseId();
        this.exerciseTitle = title;
        this.studentId = code.getStudentId();
        this.dateGrade = code.getDateGrade();
        this.dateSubmission = code.getDateSubmit();
        this.type = "code";
        this.score = code.getScore();
    }
    public AllStudentSubmissionResponse(EssaySubmission essay, String title){
        this.submissionId = essay.getSubmissionId();
        this.exerciseId = essay.getExerciseId();
        this.exerciseTitle = title;
        this.studentId = essay.getStudentId();
        this.dateGrade = essay.getDateGrade();
        this.dateSubmission = essay.getDateSubmit();
        this.type = "essay";
        this.score = essay.getScore();
    }
}
