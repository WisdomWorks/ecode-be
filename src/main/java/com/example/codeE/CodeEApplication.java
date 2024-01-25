package com.example.codeE;

import com.example.codeE.model.user.StudentEnrollment;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2
public class CodeEApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeEApplication.class, args);
		StudentEnrollment b = new StudentEnrollment();
	}

}
