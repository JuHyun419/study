package com.example.designpattern.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaticPrinterTest {

    private static final int NUMBER = 5;

    @Test
    void 자원을_공유하는_멀티_스레드() {
        StaticUserThread[] user = new StaticUserThread[NUMBER];

        for (int i = 0; i < NUMBER; i++) {
            user[i] = new StaticUserThread((i + 1) + "-thread");
            user[i].start();
        }
    }

}