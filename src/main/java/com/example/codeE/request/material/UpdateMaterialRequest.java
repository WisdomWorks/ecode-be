package com.example.codeE.request.material;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaterialRequest {

    @NotBlank(message = "Material ID is required")
    private String materialId;
    private String description;
    @NotBlank(message = "Check all status is required")
    private boolean isCheckAll;
}
