package com.example.codeE.request.material;

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
public class CreateMaterialRequest {

    @NotBlank(message = "Material type is required")
    @Size(max = 6, message = "Max material type is 6")
    private String materialType;

    @NotBlank(message = "Topic ID is required")
    @Size(max = 36, message = "TopicID is GUID type")
    private String topicId;

    private String storageUrl;

    private String description;
}
