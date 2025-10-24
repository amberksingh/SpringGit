package com.example.SpringGit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SpringGitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGitApplication.class, args);
	}

}
