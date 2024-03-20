package com.example.codeE.request.material;

import com.example.codeE.validator.id.ExistingId;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {CreateMaterialRequest.class})
public class CreateMaterialRequest {

    @Column(name = "material_name")
    @NotBlank(message = "Material name is required")
    private String materialName;

    @NotBlank(message = "Material type is required")
    @Pattern(regexp = "^(file|string)$", message = "Invalid material type. Allowed types are file and link.")
    private String materialType;

    @NotBlank(message = "Topic ID is required")
    @Size(max = 36, message = "TopicID is GUID type")
    private String topicId;

    private String url;

    private String description;

    private MultipartFile file;
}
