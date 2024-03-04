package com.example.codeE.request.material;

import com.example.codeE.request.course.UpdateCourseRequest;
import com.example.codeE.validator.id.ExistingId;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {UpdateMaterialRequest.class})
public class UpdateMaterialRequest {

    @NotBlank(message = "Material ID is required")
    private String materialId;

    @Size(max = 6, message = "Max material type is 6")
    private String materialType;

    @Size(max = 36, message = "TopicID is GUID type")
    private String topicId;

    private String storageUrl;

    private String description;
}
