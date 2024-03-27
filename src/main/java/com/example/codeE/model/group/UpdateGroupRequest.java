package com.example.codeE.model.group;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGroupRequest {

    @NotBlank(message = "Group name can not empty")
    private String GroupName;
}
