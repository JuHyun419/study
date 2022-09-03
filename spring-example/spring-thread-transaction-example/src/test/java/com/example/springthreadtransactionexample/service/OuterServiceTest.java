package com.example.springthreadtransactionexample.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OuterServiceTest {

    @Autowired
    private OuterService outerService;

    @Autowired
    private InnerService innerService;

    @Test
    void transactionNewTest() {
        outerService.outerMethod();
    }
}