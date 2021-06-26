package com.example.reflection;

import java.util.Arrays;

public class App {

    public static void main(String[] args) throws ClassNotFoundException {
        // 클래스 정보 얻는 방법 1
        Class<Book> bookClass = Book.class;

        // 클래스 정보 얻는 방법 2
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        // 클래스 정보 얻는 방법 3(jdbc)
        Class<?> aClass1 = Class.forName("com.example.reflection.Book");

        // return public access
        Arrays.stream(bookClass.getFields()).forEach(System.out::println);

        // return all fields
        Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);

        // private access => setAccessible(true)


        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                if (a instanceof MyAnnotation) {
                    MyAnnotation my = (MyAnnotation) a;
                    System.out.println(my.value() + " " + my.number());
                }
            });
        });
    }
}
