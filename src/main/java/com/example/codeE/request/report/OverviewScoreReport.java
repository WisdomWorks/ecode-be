package com.example.codeE.request.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OverviewScoreReport {
    private int AScore;
    private int BScore;
    private int CScore;
    private int numberSubmission;
    private int numberStudent;
}
