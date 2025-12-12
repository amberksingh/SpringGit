package com.example.SpringGit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication(
        //disable the default web server as below
//		exclude = {
//				org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.class
//		}
)
@EnableRetry
public class SpringGitApplication {


	public static void main(String[] args) {
		//How to disable the web server

//		SpringApplication app = new SpringApplication(SpringGitApplication.class);
//      app.setWebApplicationType(WebApplicationType.NONE);
//		app.run(args);

        //•	Boot normally creates a ServletWebServerApplicationContext (with Tomcat).
        //•	When you set WebApplicationType.NONE, Boot switches to a generic ApplicationContext (no web environment).
        //•	Your beans (@Service, @Component, etc.) still load normally.
        //•	Any ApplicationRunner or CommandLineRunner still execute.

		SpringApplication.run(SpringGitApplication.class, args);
	}

}
