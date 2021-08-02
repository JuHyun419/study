package com.example.springbootcicdexample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @RequestMapping("/sample")
    public String sample() {
        return "sample!!";
    }
}
