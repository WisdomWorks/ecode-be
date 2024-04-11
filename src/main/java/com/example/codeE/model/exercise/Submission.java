package com.example.codeE.model.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.codeE.constant.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Submission")
public class Submission {
    @Id
    private String submissionId;
    @Field
//    @NotNull(message = "Student ID is required")
    private String studentId;
    @Field
//    @NotNull(message = "Exercise ID is required")
    private String exerciseId;
    private Float score = -1.0f;
    @Field
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private String dateSubmit;
    @Field
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private String dateGrade;
    @Field
    private String teacherComment;
    @Field
//    @NotNull(message = "Reviewable is required")
    private boolean reviewable;
    public Submission(String studentId, String exerciseId, boolean reviewable, String teacherComment) {
        this.studentId = studentId;
        this.exerciseId = exerciseId;
        this.reviewable = reviewable;
        this.teacherComment = teacherComment;
    }
    public Submission(String studentId, String exerciseId, Float score, boolean reviewable, String teacherComment) {
        this.studentId = studentId;
        this.exerciseId = exerciseId;
        this.score = score;
        this.reviewable = reviewable;
        this.teacherComment = teacherComment;
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
