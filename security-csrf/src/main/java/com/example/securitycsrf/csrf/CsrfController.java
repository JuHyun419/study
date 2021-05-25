package com.example.securitycsrf.csrf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/csrf")
@RestController
public class CsrfController {

    @GetMapping
    public void csrfGet() {
        log.info("GET");
    }

    @PostMapping
    public void csrfPost() {
        log.info("POST");
    }
}
