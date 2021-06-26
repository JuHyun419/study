package com.example.disample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    void di_주입받은_객체는_NotNull이어야_한다() {
        assertNotNull(bookService);
        assertNotNull(bookService.bookRepository);
    }
}