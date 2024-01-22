package com.example.codeE.model.user;

import java.util.Date;

import lombok.*;

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
