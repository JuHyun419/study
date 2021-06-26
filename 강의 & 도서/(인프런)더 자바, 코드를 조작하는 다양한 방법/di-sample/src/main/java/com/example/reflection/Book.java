package com.example.reflection;

public class Book {

    @MyAnnotation
    private String a;

    private static String B = "BOOK";

    private static final String C = "BOOK";

    @MyAnnotation(value = "String d", number = 150)
    public String d = "d";

    protected String e = "e";

    public Book() {
    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("F");
    }

    public void g() {
        System.out.println("g");
    }

    public int h() {
        return 100;
    }


}
