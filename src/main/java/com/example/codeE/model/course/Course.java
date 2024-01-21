package com.example.codeE.model.course;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Course {
    @NonNull
    private String courseId;
    @NonNull
    private String courseName;
    @NonNull
    private String mainTeacher;
    private String semester;
    @NonNull
    private Date dateCreate;
    private String description;
    @NonNull
    private String createBy;
}
