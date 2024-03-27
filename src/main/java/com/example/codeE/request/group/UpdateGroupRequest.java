package com.example.codeE.request.group;


import com.example.codeE.validator.id.ExistingId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExistingId(targetClasses = {CreateGroupRequest.class})
public class UpdateGroupRequest {
    private String groupId;
}
