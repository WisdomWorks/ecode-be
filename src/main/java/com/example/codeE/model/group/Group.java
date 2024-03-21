package com.example.codeE.model.group;

import java.time.LocalDateTime;
import java.util.List;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.topic.ViewPermissionTopic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.codeE.constant.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "group")
@Table(name = "`group`")

public class Group {
    @Id
    @NotBlank(message = "Group id is required")
    @Column(name = "group_id")
    private String groupId;

    @NotBlank(message = "Course id is required")
    @Column(name = "course_id")
    private String courseId;
    
    @NotBlank(message = "Group name is required")
    @Column(name = "group_name")
    private String groupName;
    
    @Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    @Column(name = "updated_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updateDate;

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupStudent> groupStudents;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "course_id", insertable=false, updatable=false)
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<ViewPermissionTopic> viewPermissionTopics;
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createDate = now;
        this.updateDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }
}
