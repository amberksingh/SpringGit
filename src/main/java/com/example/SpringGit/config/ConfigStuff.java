package com.example.SpringGit.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigStuff {

    @PostConstruct
    void postConstruct() {
        System.out.println("Inside main branch postConstruct() method of configuration...");
    }

    @PreDestroy
    void preDestroy() {
        System.out.println("Inside main branch preDestroy() method of configuration...");
    }
}
