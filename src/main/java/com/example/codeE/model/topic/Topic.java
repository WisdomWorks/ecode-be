package com.example.codeE.model.topic;

import com.example.codeE.model.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String createdDate;

    @Column(name = "updated_date")
    private String updatedDate;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "course_id", insertable=false, updatable=false)
    private Course course;

    public Topic(String topicId) {
        this.topicId = topicId;
    }
}
