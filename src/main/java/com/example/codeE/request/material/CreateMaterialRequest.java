package com.example.codeE.request.material;

import com.example.codeE.validator.id.ExistingId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {CreateMaterialRequest.class})
public class CreateMaterialRequest {

    @NotBlank(message = "Material type is required")
    @Pattern(regexp = "^(file|folder)$", message = "Invalid material type. Allowed types are file and folder.")
    private String materialType;

    @NotBlank(message = "Topic ID is required")
    @Size(max = 36, message = "TopicID is GUID type")
    private String topicId;

    private String storageUrl;

    private String description;
}
