package com.example.codeE.model.group;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.user.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity(name = "group_student")
@Table(name = "group_student")
public class GroupStudent {
    @Id
    @NotBlank(message = "Group id is required")
    @Column(name = "group_id")
    private String groupId;

    @NotBlank(message = "Student id is required")
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "description")
    private String description;

    @Column(name = "join_date", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime joinDate;

    @PrePersist
    protected void onCreate() {
        this.joinDate = LocalDateTime.now();
    }

    public GroupStudent(String studentId, String groupId, String description){
        this.groupId = groupId;
        this.studentId = studentId;
        this.description = description;
    }
}
