package com.example.codeE.model.course;

import com.example.codeE.model.topic.Topic;
import com.example.codeE.util.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Entity(name = "course")
public class Course {
    @Id
    @NotBlank(message = "Course id is required")
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_name")
    @NotBlank(message = "Course name is required")
    private String courseName;

    @Column(name = "main_teacher")
    @NotBlank(message = "Teacher ID is required")
    private String mainTeacher;

    @Column(name = "semester")
    @NotBlank(message = "Semester is required")
    private String semester;

    @Column(name = "created_date")
    @NotBlank(message = "Created date is required")
    private String createdDate;

    @Column(name = "updated_date")
    @NotBlank(message = "Updated date is required")
    private String updatedDate;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Topic> topics;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = DateTimeUtil.format(now);
        updatedDate = DateTimeUtil.format(now);
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = DateTimeUtil.format(LocalDateTime.now());
    }
}
