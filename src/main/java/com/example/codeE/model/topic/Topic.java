package com.example.codeE.model.topic;

import com.example.codeE.model.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "topic")
@Table(name = "topic")
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

    @Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "course_id", insertable=false, updatable=false)
    private Course course;

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
    public Topic(String topicId) {
        this.topicId = topicId;
    }
}
