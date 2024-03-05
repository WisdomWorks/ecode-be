package com.example.codeE.request.course;

import com.example.codeE.validator.id.ExistingId;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {com.example.codeE.request.course.RemoveStudentFromCourseRequest.class})
public class RemoveStudentFromCourseRequest {

    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotBlank(message = "Course ID is required")
    private String courseId;
}