package com.example.codeE.model.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeacherEnrollment {
    @NonNull
    private String teacherId;
    @NonNull
    private String courseId;
    private String description;
    @NonNull
    private Date dateEnroll;
}
