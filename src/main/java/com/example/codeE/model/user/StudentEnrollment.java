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
public class StudentEnrollment {
    @NonNull
    private String studentId;
    @NonNull
    private String courseId;
    @NonNull
    private Date dateEnroll;
    @NonNull
    private String description;
}
