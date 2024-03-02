package com.example.codeE.mapper.course;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseFromExcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_name", columnDefinition = "TEXT")
    private String courseName;

    @Column(name = "semester", length = 4)
    private String semester;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;
}
