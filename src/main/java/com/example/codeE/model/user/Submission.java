package com.example.codeE.model.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Submission {
    @NonNull
    private String studentId;
    @NonNull 
    private String exerciseId;
    @NonNull
    private String submission;
    private float score;
    @NonNull
    private Date dateSubmit;
    private Date dateGrade;
    @NonNull
    private Boolean Reviewable;
}
