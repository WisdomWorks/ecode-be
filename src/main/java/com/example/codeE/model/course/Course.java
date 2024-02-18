package com.example.codeE.model.course;

import com.example.codeE.model.topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String createdDate;

    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Topic> topics;
}
