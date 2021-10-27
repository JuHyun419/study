package com.example.designpattern.singleton;

public class StaticUserThread extends Thread {

    public StaticUserThread(String name) {
        super(name);
    }

    public void run() {
        StaticPrinter.print(Thread.currentThread().getName() + " print using ");
    }

}
