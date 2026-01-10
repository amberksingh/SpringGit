package com.example.SpringGit.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ApplicationRunnerDemo implements ApplicationRunner {


    @Autowired
    Environment environment;

    //private static final Logger log = LoggerFactory.getLogger(ApplicationRunnerDemo.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("info : Inside ApplicationArguments run method..");
        log.debug("debug : Inside ApplicationArguments run method..");

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
        System.out.println("Environment : Debug enabled? " + environment.getProperty("debug"));//empty/nothing prints
        System.out.println("Environment : Environment = " + environment.getProperty("env"));//prod
        System.out.println("Active profiles: " + String.join(", ", environment.getActiveProfiles()));

        //============//
        //mvn spring-boot:run "-Dspring-boot.run.profiles=dev" "-Dspring-boot.run.arguments=--debug foo bar"
        //mvn spring-boot:run "-Dspring.profiles.active=prod" "-Dspring-boot.run.arguments=--debug --env=prod foo bar"
        //All option names: [debug, env]
        //Env value: [prod]
        //Contains debug? true
        //Environment = prod
        //ApplicationArguments : Debug mode is ON
        //Non-option args: [foo, bar]
        //Environment : Debug enabled?
        //Environment : Environment = prod
        //Active profiles:

        //=============//
        //java -jar target/SpringGit-0.0.1-SNAPSHOT.jar --debug --env=prod foo bar --spring.profiles.active=prod
        //All option names: [debug, env, spring.profiles.active]
        //Env value: [prod]
        //Contains debug? true
        //Environment = prod
        //ApplicationArguments : Debug mode is ON
        //Non-option args: [foo, bar]
        //Environment : Debug enabled?
        //Environment : Environment = prod
        //Active profiles: prod


        //java -jar myapp.jar --debug --env=prod startNow
        //java -jar SpringGit-0.0.1-SNAPSHOT.jar --debug foo bar
        //java -jar SpringGit-0.0.1-SNAPSHOT.jar --debug --env=prod foo bar
        //java -jar SpringGit-0.0.1-SNAPSHOT.jar --debug --env=prod foo bar --spring.profiles.active=prod
        //java -jar SpringGit-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod --debug foo bar
        //java -jar myapp.jar --spring.profiles.active=prod

        //spring.profiles.active in properties file
        //server.ssl.enabled=true
        //server.port=8443

       //You can run a Spring Boot project directly from the command line using Maven in several ways 👇
        //
        //✅ Option 1 — Run using Spring Boot Maven Plugin (recommended)
        //
        //Make sure your project’s pom.xml contains:
        //
        //<plugin>
        //  <groupId>org.springframework.boot</groupId>
        //  <artifactId>spring-boot-maven-plugin</artifactId>
        //</plugin>
        //
        //
        //Then in the project root folder (where the pom.xml is located) run:
        //
        //mvn spring-boot:run
        //
        //
        //✅ This compiles, packages, and starts the embedded Tomcat on the port configured in your
        // application.properties (default 8080).
        //
        //✅ Option 2 — Run a pre-built jar
        //
        //If you’ve already built your jar:
        //
        //mvn clean package
        //
        //
        //Then run the jar file created in target/:
        //
        //java -jar target/your-app-name-0.0.1-SNAPSHOT.jar
        //
        //✅ Option 3 — Specify profile or port
        //
        //You can activate a Spring profile or override properties from the CLI:
        //
        //mvn spring-boot:run -Dspring-boot.run.profiles=dev
        //
        //
        //or
        //
        //mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"
        //
        //✅ Option 4 — If you use wrapper (no Maven installed globally)
        //
        //Run:
        //
        //./mvnw spring-boot:run      # Linux / macOS
        //mvnw.cmd spring-boot:run    # Windows
        //
        //🔍 Common issues
        //Issue	Fix
        //No plugin found for prefix 'spring-boot'	Ensure <spring-boot-maven-plugin> is in your pom.xml
        //Command not found	Check Maven installed and in PATH (or use wrapper)
        //App not accessible	Verify port in logs (Tomcat started on port(s): ...)
        //
        //✅ In short:
        //
        //The simplest and most common command is:
        //
        //mvn spring-boot:run
        //
        //
        //Run it from the same directory as your pom.xml.

        //You said:
        //
        //mvn spring-boot:run -Dspring-boot.run.profiles=dev
        // MVN SPRING-BOOT:RUN -DSPRING-BOOT.RUN.PROFILES=DEV
        //
        //this only i gave
        //You said:
        //
        //mvn spring-boot:run "-Dspring.profiles.active=dev"
        //
        //this works
        //You said:
        //java -jar target/SpringGit-0.0.1-SNAPSHOT.jar --debug --env=prod foo bar --spring.profiles.active=prod
        //java -jar SpringGit-0.0.1-SNAPSHOT.jar --debug --env=prod foo bar --spring.profiles.active=prod convert to using mvn
        //Perfect 🎯 — yes, that’s the right and cross-platform safe way to pass Spring profile arguments when running via Maven,
        // especially on Windows PowerShell.
        //
        //✅ This works because:
        //
        //PowerShell sometimes treats -Dspring.profiles.active=dev as a PowerShell switch (like its own -D flag).
        //
        //Wrapping it in quotes makes sure Maven, not PowerShell, interprets it.
        //
        //✅ What this command does
        //==============================================================================
        //==============================================================================
        //mvn spring-boot:run "-Dspring.profiles.active=dev"
        //MVN SPRING-BOOT:RUN "-DSPRING.PROFILES.ACTIVE=DEV"
        //
        //
        //Runs your Spring Boot app via the Spring Boot Maven Plugin
        //
        //Activates the dev profile from your application-dev.yml or application-dev.properties
        //
        //You’ll see this in your logs:
        //
        //The following profiles are active: dev
        //
        //🧩 Optional additions
        //
        //You can also combine multiple CLI arguments safely, like:
        //
        //mvn spring-boot:run "-Dspring.profiles.active=dev" "-Dspring-boot.run.arguments=--server.port=9090"
        //
        //
        //or shorter:
        //
        //mvn spring-boot:run "-Dspring.profiles.active=dev" "-Dspring-boot.run.arguments=--debug"
        //
        //
        //✅ In short:
        //
        //mvn spring-boot:run "-Dspring.profiles.active=dev"
        //→ safest, correct, and portable way to run your app with a specific Spring profile.
        //Excellent 👏 — that’s a perfect real-world scenario.
        //
        //You’re basically running a Spring Boot JAR with several CLI arguments:
        //
        //java -jar SpringGit-0.0.1-SNAPSHOT.jar --debug --env=prod foo bar --spring.profiles.active=prod
        //
        //
        //Let’s convert that properly to Maven’s spring-boot:run command form 👇
        //
        //✅ 1️⃣ Direct equivalent with mvn spring-boot:run
        //
        //When using mvn spring-boot:run, you pass all the same CLI args through the special property:
        //
        //-Dspring-boot.run.arguments="..."
        //
        //
        //So, your exact equivalent is:
        //
        //mvn spring-boot:run "-Dspring-boot.run.arguments=--debug --env=prod foo bar --spring.profiles.active=prod"
        //
        //
        //✅ Works the same as your java -jar command — Spring Boot receives all arguments after --.
        //
        //🧠 2️⃣ Optional shorter variant (split arguments cleanly)
        //
        //You can also split it into two system properties for readability:
        //
        //mvn spring-boot:run "-Dspring.profiles.active=prod" "-Dspring-boot.run.arguments=--debug --env=prod foo bar"
        //
        //
        //Both forms are identical in behavior — Spring Boot sees the same args[] inside your application.
        //
        //🧩 3️⃣ Windows PowerShell / CMD safe formats
        //Shell	Correct Syntax
        //PowerShell	mvn spring-boot:run "-Dspring.profiles.active=prod" "-Dspring-boot.run.arguments=--debug --env=prod foo bar"
        //CMD	mvn spring-boot:run -Dspring.profiles.active=prod -Dspring-boot.run.arguments="--debug --env=prod foo bar"
        //Linux / macOS	mvn spring-boot:run -Dspring.profiles.active=prod -Dspring-boot.run.arguments="--debug --env=prod foo bar"
        //⚙️ 4️⃣ What happens behind the scenes
        //
        //Both commands do this internally:
        //
        //SpringApplication.run(MainClass.class, "--debug", "--env=prod", "foo", "bar", "--spring.profiles.active=prod")
        //
        //
        //So all those flags (--debug, --env=prod, etc.) will be available via:
        //
        //SpringApplication.getDefaultProperties()
        //
        //
        //or environment lookups.
        //
        //🧾 5️⃣ Quick sanity check
        //
        //When you run it, logs should show:
        //
        //The following profiles are active: prod
        //
        //
        //and debug logging enabled.
        //
        //✅ In short:
        //
        //Your java -jar ... line converts directly to:
        //
        //mvn spring-boot:run "-Dspring-boot.run.arguments=--debug --env=prod foo bar --spring.profiles.active=prod"
        //
        //
        //or cleaner:
        //
        //mvn spring-boot:run "-Dspring.profiles.active=prod" "-Dspring-boot.run.arguments=--debug --env=prod foo bar"
        //
        //
        //🔒 Always quote the entire -D arguments on PowerShell to avoid parsing issues.
    }
}
