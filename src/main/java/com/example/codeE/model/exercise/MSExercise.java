package com.example.codeE.model.exercise;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "exercise")
public class MSExercise {
    @Id
    @Column(name = "exercise_id")
    private String exerciseId;

    @Column(name = "topic_id")
    private String topicId;
}
