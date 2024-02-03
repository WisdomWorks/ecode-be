package com.example.codeE.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private int totalRecords;
    private int pageSize;
    private int pageNumber;
    private int totalPage;
}
