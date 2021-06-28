package com.example.disample;

import com.example.reflection.Book;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultBookServiceTest {

    // BookService bookService = new DefaultBookService();

    // 클라이언트가 Proxy 사용하도록 수정
    //BookService bookService = new BookServiceProxy(new DefaultBookService());

    // 다이나믹 프록시
    BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(),
            new Class[]{BookService.class},
            new InvocationHandler() {
                BookService bookService = new DefaultBookService();
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // rent() 메소드에만 Proxy 적용
                    if (method.getName().equals("rent")) {
                        System.out.println("Proxy 호출 전");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("Proxy 호출 후");
                        return invoke;
                    }

                    return method.invoke(bookService, args);
                }
            });

    @Test
    void di_주입받은_객체는_NotNull이어야_한다() {
        assertNotNull(bookService);
    }

    @Test
    void proxy() {
        Book book = new Book();
        book.setTitle("Spring");
        bookService.rent(book);
    }
}