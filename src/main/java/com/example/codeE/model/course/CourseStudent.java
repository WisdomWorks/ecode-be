package com.example.codeE.model.course;

import com.example.codeE.model.user.User;
import com.example.codeE.request.course.AddStudentToCourseRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "course_student")
@IdClass(CourseStudentId.class)
public class CourseStudent {
    @Id
    @NotBlank(message = "Student ID is required")
    @Column(name = "student_id")
    private String studentId;

    @Id
    @NotBlank(message = "Course ID is required")
    @Column(name = "course_id")
    private String courseId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id", referencedColumnName = "user_id", insertable = false, updatable = false)
//    private User student;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false)
//    private Course course;

    @Column(name = "join_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime joinDate;

    public CourseStudent(AddStudentToCourseRequest request) {
        this.studentId = request.getStudentId();
        this.courseId = request.getCourseId();
        this.joinDate = LocalDateTime.now();
    }

    public CourseStudent(@NotNull String studentId, @NotNull String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.joinDate = LocalDateTime.now();
    }
}
