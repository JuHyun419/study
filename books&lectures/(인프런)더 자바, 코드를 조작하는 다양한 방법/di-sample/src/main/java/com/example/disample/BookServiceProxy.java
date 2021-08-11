package com.example.disample;

import com.example.reflection.Book;

// 프록시
public class BookServiceProxy implements BookService {

    // 프록시(Proxy) -> 리얼 서브젝트(DefaultBookService)를 가지고 있어야 함
    BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("Proxy로 추가 전");
        bookService.rent(book);
        System.out.println("Proxy로 추가 후");
    }
}
