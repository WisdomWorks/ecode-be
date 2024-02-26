package com.example.codeE;

import com.example.codeE.model.docker.DockerApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class CodeEApplication {
	public static void main(String[] args) {
		SpringApplication.run(CodeEApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			DockerApplication dockerApplication = new DockerApplication();
			String currentDirectory = System.getProperty("user.dir");

			String imageId = dockerApplication.buildDockerImage(new File(currentDirectory, "Dockerfile.java"));
			System.out.println("Image ID: " + imageId);
			String containerId = dockerApplication.createContainer(imageId);
			System.out.println("Container ID: " + containerId);
			// Invoke mvn test in the container
//			String output = dockerApplication.runCmd(containerId, "java", "Main");
		};
	}
}
