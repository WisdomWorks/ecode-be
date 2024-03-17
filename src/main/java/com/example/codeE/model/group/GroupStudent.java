package com.example.codeE.model.group;
import com.example.codeE.constant.Constant;
import com.example.codeE.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@IdClass(GroupStudentId.class)
public class GroupStudent {
    @Id
    @NotBlank(message = "Group id is required")
    @Column(name = "group_id")
    private String groupId;
    @Id
    @NotBlank(message = "Student id is required")
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "description")
    private String description;

    @Column(name = "join_date", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime joinDate;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "student_id", insertable=false, updatable=false)
    private User student;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "group_id", insertable=false, updatable=false)
    private Group group;
    @PrePersist
    protected void onCreate() {
        this.joinDate = LocalDateTime.now();
    }

    public GroupStudent(String studentId, String groupId, String description){
        this.groupId = groupId;
        this.studentId = studentId;
        this.description = description;
        this.joinDate = LocalDateTime.now();
    }
}
