package com.example.codeE.model.exercise;

import com.example.codeE.request.exercise.file.CreateFileSubmissionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "FileSubmission")
public class FileSubmission extends Submission {
    @Field
    private String fileUrl;

    public FileSubmission(CreateFileSubmissionRequest request, float score) {
        super(request.getStudentId(), request.getExerciseId(), score, true, "");
        this.fileUrl = request.getUrl();
    }
}
