package com.example.codeE.model.course;

import com.example.codeE.constant.Constant;
import com.example.codeE.mapper.course.CourseFromExcel;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.request.course.CreateCourseRequest;
import com.example.codeE.security.BCryptPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Entity(name="course")
public class Course {
    @Id
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

    @Column(name = "enroll_key")
    private String enrollKey;

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

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Topic> topics;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseStudent> courseStudents;
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseTeacher> courseTeachers;
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }

    public Course(CourseFromExcel excelCourse){
        this.courseId = excelCourse.getCourseId();
        this.courseName = excelCourse.getCourseName();
        this.semester = excelCourse.getSemester();
        this.enrollKey = BCryptPassword.generateRandomPassword();
        this.description = excelCourse.getDescription();
    }
    public Course(CreateCourseRequest courseRequest, String courseId){
        this.courseId = courseId;
        this.courseName = courseRequest.getCourseName();
        this.semester = courseRequest.getSemester();
        this.enrollKey = BCryptPassword.generateRandomPassword();
        this.description = courseRequest.getDescription();
    }
}
