package com.example.codeE.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {
    private String role;
    private String searchKeyword;
    private Pageable pageable;
}
