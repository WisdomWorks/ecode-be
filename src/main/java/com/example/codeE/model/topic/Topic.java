package com.example.codeE.model.topic;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.exercise.MSExercise;
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
@Entity(name = "topic")
public class Topic {
    @Id
    @NotBlank(message = "Topic id is required")
    @Column(name = "topic_id")
    private String topicId;

    @Column(name = "course_id")
    @NotBlank(message = "Course ID is required")
    private String courseId;

    @Column(name = "topic_name")
    @NotBlank(message = "Topic name is required")
    private String topicName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_public")
    @NotBlank(message = "Topic public option is required")
    private Boolean isPublic;

    @Column(name = "created_date")
    @NotBlank(message = "Created date is required")
    private String createdDate;

    @Column(name = "updated_date")
    @NotBlank(message = "Updated date is required")
    private String updatedDate;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "course_id", insertable=false, updatable=false)
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "topic")
    private List<MSExercise> exercises;

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

    public Topic(String topicId) {
        this.topicId = topicId;
    }
}
