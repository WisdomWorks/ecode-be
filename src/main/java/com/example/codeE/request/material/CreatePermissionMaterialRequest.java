package com.example.codeE.request.material;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermissionMaterialRequest {
    @NotNull(message = "Material ID is required")
    private String materialId;
    private List<String> groupIds;
    @NotNull(message = "Show all status is required")
    boolean isShowAll = true;
}
