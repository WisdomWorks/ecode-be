package com.example.codeE.model.course;

import com.example.codeE.mapper.course.CourseFromExcel;
import com.example.codeE.model.topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.CascadeType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private String courseName;

    @Column(name = "semester", length = 4)
    @NotBlank(message = "Semester is required")
    private String semester;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Topic> topics;

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
        this.description = excelCourse.getDescription();
    }


}
