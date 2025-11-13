package com.example.SpringGit.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class MyStartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

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




    }
}
