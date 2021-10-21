package com.example.designpattern.singleton;

public class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public void print() {
        Printer printer = Printer.getInstance();
        printer.print(this.name + " print using " + printer);
    }
}
