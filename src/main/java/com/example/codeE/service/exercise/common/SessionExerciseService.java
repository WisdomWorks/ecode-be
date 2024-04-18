package com.example.codeE.service.exercise.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SessionExerciseService {
    void removeSession(HttpServletResponse response, HttpServletRequest request);
}
