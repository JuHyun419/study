package com.example.customdi;

import com.example.disample.BookRepository;
import com.example.disample.BookService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerServiceTest {

    @Test
    void getObject() {
        BookRepository bookRepository = ContainerService.getObject(BookRepository.class);
        assertNotNull(bookRepository);
    }

    @Test
    void getObject_BookService() {
        BookService bookService = ContainerService.getObject(BookService.class);
        assertNotNull(bookService);
    }
}