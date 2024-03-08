package com.example.codeE.model.course;
import com.example.codeE.constant.Constant;
import com.example.codeE.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "course_teacher")
@IdClass(CourseTeacherId.class)
public class CourseTeacher {
    @Id
    @NotBlank(message = "Teacher ID is required")
    @Column(name = "teacher_id")
    private String teacherId;

    @Id
    @NotBlank(message = "Course ID is required")
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "is_main")
    private Boolean isMain = false;

    @Column(name = "created_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime createdDate;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "course_id", insertable=false, updatable=false)
    private Course course;

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "teacher_id", insertable=false, updatable=false)
    private User teacher;

    public CourseTeacher(String teacherId, String courseId, Boolean isMain){
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.isMain = isMain;
    }
}