package com.example.codeE.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4000", "http://localhost:4001")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }
}
