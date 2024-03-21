package com.example.codeE.request.group;

import com.example.codeE.validator.id.ExistingId;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {CreateGroupRequest.class})
public class CreateGroupRequest {

    @NotBlank(message = "Course id is required")
    private String courseId;
    @NotBlank(message = "Group Name id is required")
    private String groupName;
    
}
