package pl.edu.pjatk.ProjectMPR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectMprApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMprApplication.class, args);
		System.out.println("Server listening on http://localhost:8080");
	}

}
