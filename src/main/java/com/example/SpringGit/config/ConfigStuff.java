package com.example.SpringGit.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigStuff {

    @PostConstruct
    void postConstruct() {
        System.out.println("Message : Inside feature branch postConstruct() method inside config...");
    }

    @PreDestroy
    void preDestroy() {
        System.out.println("Message : Inside feature branch preDestroy() method inside config...");
    }
}
