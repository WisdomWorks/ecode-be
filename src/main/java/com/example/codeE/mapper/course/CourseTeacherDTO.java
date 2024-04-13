package com.example.codeE.mapper.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseTeacherDTO {
    private String teacherId;
    private Boolean isMain;
}
