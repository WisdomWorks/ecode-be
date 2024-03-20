package com.example.codeE.model.judge.enumbase;

public enum Status {
    QU("Queued"),
    P("Processing"),
    G("Grading"),
    D("Completed"),
    IE("Internal Error"),
    CE("Compile Error"),
    AB("Aborted");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
