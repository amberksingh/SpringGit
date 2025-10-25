package com.example.SpringGit.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigStuff {

    @PostConstruct
    void postConstruct() {
        System.out.println("Inside stash on main branch postConstruct() method inside configuration class...");
    }

    @PreDestroy
    void preDestroy() {
        System.out.println("Inside stash on main branch preDestroy() method inside configuration class...");
    }
}
