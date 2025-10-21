package com.example.SpringGit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("git")
public class EntryController {

//    @PostMapping("/post")
//    public ResponseEntity<Question> post(@RequestBody @Valid Question question) {
//        System.out.println("inside /post");
//        return new ResponseEntity<>(repo.save(question), CREATED);
//    }

    @GetMapping("/get")
    public ResponseEntity<String> get() {

        System.out.println("Inside get() method...");
        return ResponseEntity.ok("Inside get() method...");
    }
}
