package com.example.codeE;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2
@Configuration
public class CodeEApplication {
	public static void main(String[] args) {
		SpringApplication.run(CodeEApplication.class, args);
	}
}
