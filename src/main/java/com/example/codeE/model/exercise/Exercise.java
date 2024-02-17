package com.example.codeE.model.exercise;

import com.example.codeE.validator.date.DateComparison;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Exercise")
@DateComparison(targetClasses = {Exercise.class})
public class Exercise {
    @Id
    private String exerciseId;

    @Field
    @NotNull(message = "Topic ID is required")
    private String topicId;

    @Field
    @NotNull(message = "Exercise's name is required")
    private String exerciseName;

    @Field
    @NotNull(message = "Exercise's key is required")
    private String key;

    @Field
    @CreatedDate
    private String createdDate;

    @Field
    @LastModifiedDate
    private String updatedDate;

    @Field
    @NotNull(message = "Exercise start time is required")
    @Future(message = "Exercise start time must be in the future")
    private Date startTime;

    @Field
    @NotNull(message = "Exercise end time is required")
    @Future(message = "Exercise end time must be in the future")
    private Date endTime;

    @Field
    @NotNull(message = "Exercise type is required")
    @Pattern(regexp = "^(quiz|essay|code)$", message = "Exercise type should be quiz, essay, or code")
    private String type;

    @Field
    @NotNull(message = "Exercise public option is required")
    private Boolean isPublic;

    public Exercise(String topicId, String exerciseName, String key, Date startTime, Date endTime, String type, Boolean isPublic) {
        this.topicId = topicId;
        this.exerciseName = exerciseName;
        this.key = key;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.isPublic = isPublic;
    }
}
