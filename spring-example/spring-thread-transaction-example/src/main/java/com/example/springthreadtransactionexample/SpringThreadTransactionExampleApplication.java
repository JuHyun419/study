package com.example.springthreadtransactionexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringThreadTransactionExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringThreadTransactionExampleApplication.class, args);
    }

}
