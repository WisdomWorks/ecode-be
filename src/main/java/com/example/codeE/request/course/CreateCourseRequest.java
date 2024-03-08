package com.example.codeE.request.course;

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
public class CreateCourseRequest {
    @NotBlank(message = "Course name is required")
    @Column(name = "course_name", columnDefinition = "TEXT")
    @Size(max = 255, message = "Course name cannot exceed 255 characters")
    private String courseName;

    @Column(name = "semester")
    @Size(max = 4, message = "Semester cannot exceed 4 characters")
    private String semester;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @NotBlank(message = "Teacher ID is required")
    @Column(name = "teacher_id")
    private String teacherId;
}
