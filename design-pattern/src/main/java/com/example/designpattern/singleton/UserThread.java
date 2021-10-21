package com.example.designpattern.singleton;

public class UserThread extends Thread {

    public UserThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        Printer printer = Printer.getInstance();
        printer.print(Thread.currentThread().getName() + " print using " + printer);
    }
}
