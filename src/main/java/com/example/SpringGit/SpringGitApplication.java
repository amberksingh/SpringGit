package com.example.SpringGit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication(
        //disable the default web server as below
//		exclude = {
//				org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.class
//		}
)
//@EnableRetry
public class SpringGitApplication {

    //private static final Logger log = LoggerFactory.getLogger(SpringGitApplication.class);


	public static void main(String[] args) {

//        ApplicationContext context = new WebServerApplicationContext() {
//        }

		//How to disable the web server
        //Spring context still runs
        //
        //If any non-daemon thread exists, JVM stays alive
//      app doesn't exit after finishing lets say commandline runner..as in backgrounds non-daemon threads keep running.
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
