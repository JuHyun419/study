package com.example.reflection;

public class Book {

    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
