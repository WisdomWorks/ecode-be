package com.example.codeE.security;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4000", "http://localhost:4001")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(false).maxAge(3600);
    }

}

// .allowedOrigins(cors.getAllowedOrigins())
//         .allowedMethods(cors.getAllowedMethods())
//         .maxAge(cors.getMaxAge())
//         .allowedHeaders(cors.getAllowedHeaders())
//         .exposedHeaders(cors.getExposedHeaders());
