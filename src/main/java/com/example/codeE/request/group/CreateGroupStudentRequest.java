package com.example.codeE.request.group;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateGroupStudentRequest {
    @NotBlank(message = "student Id is required")
    private List<String> studentIds;
    private String description;
    @NotBlank(message = "Group Id id is required")
    private String groupId;

}

