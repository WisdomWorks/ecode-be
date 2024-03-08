package com.example.codeE.request.course;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CourseResponse {
    @NotBlank(message = "Course id is required")
    @Column(name = "course_id")
    private String courseId;

    @NotBlank(message = "Course name is required")
    @Column(name = "course_name", columnDefinition = "TEXT")
    @Size(max = 255, message = "Course name cannot exceed 255 characters")
    private String courseName;

    @Column(name = "semester")
    @Size(max = 4, message = "Semester cannot exceed 4 characters")
    private String semester;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @NotNull(message = "Creation date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    @Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @NotNull(message = "Updated date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    @Column(name = "updated_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;

    private List<User> students;

    private User teacher;
    public CourseResponse(Course course){
        this.courseId = course.getCourseId();
        this.courseName = course.getCourseName();
        this.semester = course.getSemester();
        this.description = course.getDescription();
        this.updatedDate = course.getUpdatedDate();
        this.createdDate = course.getCreatedDate();
    }
    public CourseResponse(Course course, List<User> students, User teacher){
        this.courseId = course.getCourseId();
        this.courseName = course.getCourseName();
        this.semester = course.getSemester();
        this.description = course.getDescription();
        this.updatedDate = course.getUpdatedDate();
        this.createdDate = course.getCreatedDate();
        this.students = students;
        this.teacher = teacher;
    }
}
