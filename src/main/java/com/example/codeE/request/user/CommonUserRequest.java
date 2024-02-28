package com.example.codeE.request.user;

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
@ExistingId(targetClasses = {CommonUserRequest.class})
public class CommonUserRequest {
    @NotBlank(message = "User ID is required")
    private String userId;
}
