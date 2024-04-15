package com.example.codeE.service.exercise.common;

import com.example.codeE.repository.SessionExerciseRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SessionExerciseImpl implements SessionExerciseService{

    @Autowired
    private SessionExerciseRepository sessionExerciseRepository;

    @Override
    public void removeSession(HttpServletResponse response, HttpServletRequest request, String exerciseId) {
        var sessionList = this.sessionExerciseRepository.findAll();
        String loginId = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("LoginSessionId".equals(cookie.getName())) {
                    loginId = cookie.getValue();
                }
            }
        }
        for (var item: sessionList){
            if(Objects.equals(item.getLoginId(), loginId)){
                this.sessionExerciseRepository.delete(item);
            }
        }
    }
}
