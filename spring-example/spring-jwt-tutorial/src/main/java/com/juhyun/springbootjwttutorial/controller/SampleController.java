package com.juhyun.springbootjwttutorial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/sample")
    public ResponseEntity<String> sample() {
        return ResponseEntity.ok("Hello SpringBoot");
    }

}
