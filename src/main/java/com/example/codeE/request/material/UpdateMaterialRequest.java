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
@ExistingId(targetClasses = {UpdateMaterialRequest.class})
public class UpdateMaterialRequest {

    @NotBlank(message = "Material ID is required")
    private String materialId;

    private String description;
}
