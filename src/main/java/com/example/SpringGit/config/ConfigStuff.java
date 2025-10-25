package com.example.SpringGit.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigStuff {

    @PostConstruct
    void postConstruct() {
        System.out.println("Inside feature branch postConstruct() method of config...");
    }

    @PreDestroy
    void preDestroy() {
        System.out.println("Inside feature branch preDestroy() method of config...");
    }
}
