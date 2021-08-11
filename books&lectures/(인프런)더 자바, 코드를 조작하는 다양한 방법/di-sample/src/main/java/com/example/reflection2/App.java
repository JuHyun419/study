package com.example.reflection2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        // 클래스 정보 얻는 방법 1
        Class<Books> bookClass = Books.class;

        // 클래스 정보 얻는 방법 2
        Books books = new Books();
        Class<? extends Books> aClass = books.getClass();

        // 클래스 정보 얻는 방법 3(jdbc)
        Class<?> aClass1 = Class.forName("com.example.reflection2.Books");

        Constructor<?> constructor = aClass1.getConstructor(String.class);
        Books newBooks = (Books) constructor.newInstance("JuHyun");

        System.out.println(newBooks);

        // static => 인스턴스에 해당하지 않으므로 null
        Field a = Books.class.getDeclaredField("A");
        System.out.println(a.get(null));  // A
        a.set(null, "AAAAAA");
        System.out.println(a.get(null)); // AAAAAA

        Field b = Books.class.getDeclaredField("B");
        b.setAccessible(true); // private access
        System.out.println(b.get(books)); // B - books이라는 인스턴스에 존재하는 필드를 가져옴
        b.set(books, "BBBBBB");
        System.out.println(b.get(books)); // BBBBBB

        Method c = Books.class.getDeclaredMethod("c");
        c.setAccessible(true);
        c.invoke(books); // C - books 인스턴스, c 메서드는 파라미터가 없기 때문에 인자 1개만 설정

        Method d = Books.class.getDeclaredMethod("d", int.class, int.class); // class 타입
        int invoke = (int) d.invoke(books, 10, 20);
        System.out.println(invoke); // 30

    }
}
