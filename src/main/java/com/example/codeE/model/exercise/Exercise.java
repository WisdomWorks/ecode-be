package com.example.codeE.model.exercise;

import com.example.codeE.constant.Constant;
import com.example.codeE.validator.date.DateComparison;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.PrePersist;
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
import java.util.List;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private String createdDate;

    @Field
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private String updatedDate;

    @Field
    @NotNull(message = "Exercise start time is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private Date startTime;

    @Field
    @NotNull(message = "Exercise end time is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_ISO_FORMAT)
    private Date endTime;

    @Field
    @NotNull(message = "duration time is required")
    private int durationTime;

    @Field
    private boolean isShowAll;

    @Field
    @NotNull(message = "Re-Attempt is required")
    private int reAttempt;

    @Field
    private String exerciseDescription;
    
    @Field
    @NotNull(message = "Exercise type is required")
    @Pattern(regexp = "^(quiz|essay|code|file)$", message = "Exercise type should be quiz, essay, code or file.")
    private String type;

    @Field
    @NotNull(message = "Exercise public option is required")
    private List<String> publicGroupIds;

    public Exercise(String topicId, String exerciseName, String key, Date startTime, Date endTime, String type, List<String> publicGroupIds) {
        this.topicId = topicId;
        this.exerciseName = exerciseName;
        this.key = key;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.publicGroupIds = publicGroupIds;
    }

    @PrePersist
    protected void onCreate() {
        this.isShowAll = true;
    }

    public Exercise(String topicId, String exerciseName, String key, Date startTime, Date endTime, int durationTime, int reAttempt, String type, String exerciseDescription, boolean isShowAll, List<String> publicGroupIds) {
        this.topicId = topicId;
        this.exerciseName = exerciseName;
        this.key = key;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationTime = durationTime;
        this.reAttempt = reAttempt;
        this.type = type;
        this.isShowAll = isShowAll;
        this.exerciseDescription = exerciseDescription;
        this.publicGroupIds = publicGroupIds;
    }

    public Exercise(String exerciseId, String topicId, String exerciseName, String key, Date startTime, Date endTime, int durationTime, int reAttempt, String type, String exerciseDescription, boolean isShowAll, List<String> publicGroupIds) {
        this.exerciseId = exerciseId;
        this.topicId = topicId;
        this.exerciseName = exerciseName;
        this.key = key;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationTime = durationTime;
        this.reAttempt = reAttempt;
        this.type = type;
        this.isShowAll = isShowAll;
        this.exerciseDescription = exerciseDescription;
        this.publicGroupIds = publicGroupIds;
    }
}
