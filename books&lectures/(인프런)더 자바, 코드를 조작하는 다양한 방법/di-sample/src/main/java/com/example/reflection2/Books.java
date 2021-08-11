package com.example.reflection2;

public class Books {

    public static String A = "A";

    private String B = "B";

    public Books() {
    }

    public Books(String b) {
        B = b;
    }

    private void c() {
        System.out.println("C");
    }

    public int d(int left, int right) {
        return left + right;
    }
}
