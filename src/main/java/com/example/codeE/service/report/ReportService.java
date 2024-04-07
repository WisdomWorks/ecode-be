package com.example.codeE.service.report;

import com.example.codeE.request.report.OverviewScoreReport;

public interface ReportService {
    OverviewScoreReport getOverviewScoreReportByExerciseId(String exerciseId);
}
