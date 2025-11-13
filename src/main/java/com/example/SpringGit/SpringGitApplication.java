package com.example.SpringGit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication(
//		exclude = {
//				org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.class
//		}
)
@EnableRetry
public class SpringGitApplication {


	public static void main(String[] args) {
		//How to disable the web server

//		SpringApplication app = new SpringApplication(NoWebApp.class);
//		        app.setWebApplicationType(WebApplicationType.NONE);
//		        app.run(args);

		SpringApplication.run(SpringGitApplication.class, args);
	}

}
