package com.example.codeE.request.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExportResultExcelModel {
    private String Student_Name;
    private String Student_User_Name;
    private String Date_Submit;
    private String Date_Grade;
    private String Score;
}
