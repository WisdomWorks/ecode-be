package com.example.codeE.model.judge.enumbase;

public enum SubmissionResult {
    AC("Accepted"),
    WA("Wrong Answer"),
    TLE("Time Limit Exceeded"),
    MLE("Memory Limit Exceeded"),
    OLE("Output Limit Exceeded"),
    IR("Invalid Return"),
    RTE("Runtime Error"),
    CE("Compile Error"),
    IE("Internal Error"),
    SC("Short Circuited"),
    AB("Aborted");

    private final String description;

    SubmissionResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}