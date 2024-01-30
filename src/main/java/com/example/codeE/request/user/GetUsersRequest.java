package com.example.codeE.request.user;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersRequest {
    @Pattern(regexp = "^(Teacher|Student|Admin)$", message = "Role should be Teacher, Student, or Admin")
    private String role;

    private String searchKeyword;

    @Range(min = 1, message = "Page number must be greater than 0")
    private int pageNumber;

    private int pageSize;

    private Pageable pageable;

    public GetUsersRequest(String role, String searchKeyword, int pageNumber, int pageSize) {
        this.role = role;
        this.searchKeyword = searchKeyword;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.pageable = PageRequest.of(pageNumber-1, pageSize);
    }
}
