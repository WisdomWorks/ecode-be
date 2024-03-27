package com.example.codeE.request.group;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteStudentInGroupRequest {
    @NotBlank(message = "student Id is required")
    private List<String> studentIds;
    @NotBlank(message = "Group Id id is required")
    private String groupId;

}
