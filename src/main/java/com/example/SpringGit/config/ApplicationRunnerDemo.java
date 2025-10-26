package com.example.SpringGit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRunnerDemo implements ApplicationRunner {


    @Autowired
    Environment environment;

    private static final Logger logger = LoggerFactory.getLogger(ApplicationRunnerDemo.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.info("info : Inside ApplicationArguments run method..");
        logger.debug("debug : Inside ApplicationArguments run method..");

        System.out.println("All option names: " + args.getOptionNames());
        System.out.println("Env value: " + args.getOptionValues("env"));
        System.out.println("Contains debug? " + args.containsOption("debug"));

        List<String> envValues = args.getOptionValues("env");
        if (envValues != null && !envValues.isEmpty()) {
            System.out.println("Environment = " + envValues.get(0));
        }

        if (args.containsOption("debug")) {
            System.out.println("ApplicationArguments : Debug mode is ON");
        }

        System.out.println("Non-option args: " + args.getNonOptionArgs());

        //Using Environment variable
        System.out.println("Environment : Debug enabled? " + environment.getProperty("debug"));
        System.out.println("Environment : Environment = " + environment.getProperty("env"));
        System.out.println("Active profiles: " + String.join(", ", environment.getActiveProfiles()));


        //java -jar myapp.jar --debug --env=prod startNow
        //java -jar SpringGit-0.0.1-SNAPSHOT.jar --debug foo bar
        //java -jar SpringGit-0.0.1-SNAPSHOT.jar --debug --env=prod foo bar
        //java -jar SpringGit-0.0.1-SNAPSHOT.jar --debug --env=prod foo bar --spring.profiles.active=prod
        //java -jar SpringGit-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod --debug foo bar
        //java -jar myapp.jar --spring.profiles.active=prod

        //spring.profiles.active in properties file
        //server.ssl.enabled=true
        //server.port=8443

    }
}
