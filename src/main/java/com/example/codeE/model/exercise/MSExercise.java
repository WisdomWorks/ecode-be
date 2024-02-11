package com.example.codeE.model.exercise;

import com.example.codeE.model.topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name = "topic_id", insertable = false, updatable = false)
    private Topic topic;

    public MSExercise(String exerciseId, String topicId) {
        this.exerciseId = exerciseId;
        this.topicId = topicId;
    }
}
