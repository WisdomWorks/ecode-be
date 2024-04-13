package com.example.codeE.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubmissionInformation {
    private String userId;
    private String userName;
    private String dateSubmit;
    private float grade;
    private boolean reviewable;
}
