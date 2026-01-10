package com.example.SpringGit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyStartupRunner implements CommandLineRunner {

    @Autowired
    ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {

        //to list spring beans
        //System.out.println("context : "+ Arrays.toString(context.getBeanDefinitionNames()));

        System.out.println("CommandLineRunner: App started. Running startup logic...");
        for (String arg : args) {
            System.out.println("Argument: " + arg);
        }

        /*java -jar SpringGit-0.0.1-SNAPSHOT.jar foo bar (in CMD)

        OUTPUT:
        ======
        App started. Running startup logic...
        Argument: foo
        Argument: bar*/

        //mvn spring-boot:run "-Dspring.profiles.active=prod" "-Dspring-boot.run.arguments=--debug --env=prod foo bar"
        //Argument: --debug
        //Argument: --env=prod
        //Argument: foo
        //Argument: bar

        //=============//
        //java -jar target/SpringGit-0.0.1-SNAPSHOT.jar --debug --env=prod foo bar --spring.profiles.active=prod
        //Argument: --debug
        //Argument: --env=prod
        //Argument: foo
        //Argument: bar
        //Argument: --spring.profiles.active=prod




    }
}
