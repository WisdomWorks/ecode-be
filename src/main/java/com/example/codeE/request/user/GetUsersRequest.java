package com.example.codeE.request.user;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUsersRequest {
    @Pattern(regexp = "^(teacher|student|admin)$", message = "Role should be teacher, student, or admin")
    @Builder.Default
    private String role = null;

    @Builder.Default
    private String searchKeyword = null;

    @Range(min = 1, message = "Page number must be greater than 0")
    @Builder.Default
    private int pageNumber = 1;

    @Builder.Default
    private int pageSize = 10;

    private Pageable pageable;

    public GetUsersRequest(String role, String searchKeyword, int pageNumber, int pageSize) {
        this.role = role;
        this.searchKeyword = searchKeyword;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
