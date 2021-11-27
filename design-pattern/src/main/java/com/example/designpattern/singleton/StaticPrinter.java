package com.example.designpattern.singleton;

public class StaticPrinter {

    private static int counter = 0;

    public static synchronized void print(final String str) {
        counter++;
        System.out.println(str + " " + counter);
    }
}
