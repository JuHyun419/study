package com.juhyun.springaop.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        BookService bookService = new BookServiceProxy(new RealBookService());
        bookService.rent(new Book());
    }
}
