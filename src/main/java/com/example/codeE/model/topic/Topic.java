package com.example.codeE.model.topic;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.material.Material;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime updatedDate;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "course_id", insertable=false, updatable=false)
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<ViewPermissionTopic> viewPermissionTopics;

    @JsonIgnore
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Material> materials;

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
