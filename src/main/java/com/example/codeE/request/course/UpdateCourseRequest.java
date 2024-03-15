package com.example.codeE.request.course;

import com.example.codeE.constant.Constant;
import com.example.codeE.mapper.course.CourseTeacherDTO;
import com.example.codeE.validator.id.ExistingId;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


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

    private String enrollKey;

    private String description;

    private List<CourseTeacherDTO> teachers;
}
