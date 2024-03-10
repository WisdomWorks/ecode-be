package com.example.codeE.request.course;

import com.example.codeE.validator.id.ExistingId;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {com.example.codeE.request.course.AddStudentToCourseRequest.class})
public class AddStudentToCourseRequest {

    @NotBlank(message = "Student ID is required")
    private List<String> studentIds;

    @NotBlank(message = "Course ID is required")
    private String courseId;
}
