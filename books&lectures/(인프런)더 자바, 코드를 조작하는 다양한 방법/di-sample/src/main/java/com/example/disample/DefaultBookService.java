package com.example.disample;

import com.example.reflection.Book;

// 리얼 서브젝트
public class DefaultBookService implements BookService {

    // Proxy
    @Override
    public void rent(Book book) {
        System.out.println("rent: " + book.getTitle());
    }

}
