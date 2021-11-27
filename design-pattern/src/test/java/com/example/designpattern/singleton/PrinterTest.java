package com.example.designpattern.singleton;

import org.junit.jupiter.api.Test;

class PrinterTest {

    private static final int NUMBER = 5;

    @Test
    void 단일_스레드_프린터() {
        User[] users = new User[NUMBER];

        for (int i = 0; i < NUMBER; i++) {
            users[i] = new User((i + 1) + "-user");
            users[i].print();
        }
    }

    @Test
    void 다중_스레드_프린터() {
        UserThread[] userThreads = new UserThread[NUMBER];

        for (int i = 0; i < NUMBER; i++) {
            userThreads[i] = new UserThread((i + 1) + "-thread");
            userThreads[i].start();
        }
    }

}