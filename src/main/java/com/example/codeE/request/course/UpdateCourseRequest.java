package com.example.codeE.request.course;

import com.example.codeE.validator.id.ExistingId;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {UpdateCourseRequest.class})
public class UpdateCourseRequest {
    @NotBlank(message = "Course id is required")
    private String courseId;

    @Size(max = 255)
    private String courseName;

    @Size(max = 4)
    private String semester;

    private String description;
}
