package com.example.codeE.request.course;

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
public class UpdateStudentsToCourseRequest {
    @NotBlank(message = "Course ID is required")
    private String courseId;

    private List<String> studentIds;
}

