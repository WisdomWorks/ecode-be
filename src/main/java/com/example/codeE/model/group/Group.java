package com.example.codeE.model.group;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
    
    @Column(name = "create_date", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCreate;

    @Column(name = "update_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updateDate;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.dateCreate = now;
        this.updateDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }
}
