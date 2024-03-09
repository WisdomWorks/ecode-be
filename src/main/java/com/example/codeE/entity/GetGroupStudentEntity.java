package com.example.codeE.entity;

import jakarta.persistence.Column;
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
public class GetGroupStudentEntity {
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String userName;
    @Column(name = "join_date")
    private LocalDateTime joinDate;
}
