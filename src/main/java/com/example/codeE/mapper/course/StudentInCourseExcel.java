package com.example.codeE.mapper.course;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentInCourseExcel {
    @Column(name = "studentName")
    private String studentName;
}
