package com.example.codeE.model.exercise;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Submission")
public class Submission {
    @Id
    private String submissionId;

    @Field
    @NotNull(message = "Student ID is required")
    private String studentId;

    @Field
    @NotNull(message = "Exercise ID is required")
    private String exerciseId;

    private Float score;

    @Field
    @CreatedDate
    private String dateSubmit;

    @Field
    @LastModifiedDate
    private String dateGrade;

    @Field
    @NotNull(message = "Reviewable is required")
    private boolean reviewable;

    public Submission(String studentId, String exerciseId, boolean reviewable) {
        this.studentId = studentId;
        this.exerciseId = exerciseId;
        this.reviewable = reviewable;
    }

    public Submission(String studentId, String exerciseId, Float score, boolean reviewable) {
        this.studentId = studentId;
        this.exerciseId = exerciseId;
        this.score = score;
        this.reviewable = reviewable;
    }

    public Submission(String submissionId, String exerciseId) {
        this.submissionId = submissionId;
        this.exerciseId = exerciseId;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "submissionId='" + submissionId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", exerciseId='" + exerciseId + '\'' +
                ", score=" + score +
                ", dateSubmit=" + dateSubmit +
                ", dateGrade=" + dateGrade +
                ", reviewable=" + reviewable +
                '}';
    }
}
