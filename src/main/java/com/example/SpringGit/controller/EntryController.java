package com.example.SpringGit.controller;

import com.example.SpringGit.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpStatus.CREATED;

//To enable CORS from frontend otherwise browser blocks it
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("git")
public class EntryController {

    //    @PostMapping("/post")
//    public ResponseEntity<Question> post(@RequestBody @Valid Question question) {
//        System.out.println("inside /post");
//        return new ResponseEntity<>(repo.save(question), CREATED);
//    }
    @Autowired
    private ApiService service;

    @Value("${default.name:arun}")
    String defaultName;

    @GetMapping("/welcome")
    //@RequestMapping(method = RequestMethod.GET, headers = "key=value", params = "key=value", produces = "application/vnd.demo-v2+json")
    public ResponseEntity<String> get() {

        System.out.println(defaultName);
        System.out.println("Inside get() method...");
        return ResponseEntity.ok("Inside get() method...");
    }

    @GetMapping("/retry-demo")
    public void retryDemo() {
        System.out.println("Inside retryDemo() controller...");
        service.retryService();
    }

    @GetMapping("/payment/{paymentNum}")
    public void retryPayment(@PathVariable("paymentNum") String paymentId) {
        System.out.println("Inside retryPayment() controller...");
        service.retryPayment(paymentId);
    }

    @GetMapping("/refund/{refundNum}")
    public void retryRefund(@PathVariable("refundNum") Long refundId) {
        System.out.println("Inside retryRefund() controller...");
        service.retryRefund(refundId);
    }

    @GetMapping("/arithmetic/{value}")
    public ResponseEntity<String> simulateArithmeticException(@PathVariable String value) {
        System.out.println("Inside simulateArithmeticException() controller...");
        return service.simulateArithmeticException(value);
    }

    @GetMapping("/slow")
    public String slowEndpoint() throws InterruptedException {
        System.out.println("Received /slow request...");
        Thread.sleep(5000); // 5 seconds delay (simulating a slow service)
        return "Response after 5 seconds";
    }

    //add in config file for entire app access through frontend at http://localhost:3000
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")   // allow all endpoints
                        .allowedOrigins("http://localhost:3000") // frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }

}
