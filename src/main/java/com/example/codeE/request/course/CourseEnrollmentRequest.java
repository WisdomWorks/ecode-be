package com.example.codeE.request.course;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEnrollmentRequest {
    @NotBlank(message = "Course ID is required")
    public String courseId;
    @NotBlank(message = "Student ID is required")
    public String studentId;
    @NotBlank(message = "Enrollment Key is required")
    public String enrollmentKey;
}
