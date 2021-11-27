package com.example.designpattern.singleton;

public class Printer {

    private static volatile Printer printer;

    private Printer() { }

    private static class PrinterHolder {
        public static final Printer printer = new Printer();
    }

    // public static synchronized Printer getInstance() { ...

    /*
    public static Printer getInstance() {
        if (printer == null) {
            synchronized (Printer.class) {
                if (printer == null) {
                    printer = new Printer();
                }
            }
        }
        return printer;
    } */

    public static Printer getInstance() {
        return PrinterHolder.printer;
    }

    public void print(final String message) {
        System.out.println(message);
    }
}
