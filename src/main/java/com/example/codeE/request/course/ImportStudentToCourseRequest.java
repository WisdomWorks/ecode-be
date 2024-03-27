package com.example.codeE.request.course;

import com.example.codeE.validator.id.ExistingId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {ImportStudentToCourseRequest.class})
public class ImportStudentToCourseRequest {
    @NotBlank(message = "Course ID is required")
    private String courseId;

    @NotNull(message = "File is required")
    private MultipartFile file;
}
