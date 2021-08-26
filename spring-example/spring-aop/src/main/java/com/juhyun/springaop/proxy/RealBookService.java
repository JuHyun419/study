package com.juhyun.springaop.proxy;

import org.springframework.stereotype.Service;

/* 리얼 서브젝트 역할 */
@Service
public class RealBookService implements BookService {

    /*
        rent(): 책을 빌리는(핵심 기능) 메소드
        책을 빌리기 전에 로깅(부가 기능)을 해야 한다고 가정
        */
    public void rent(Book book) {
        System.out.println("rent: " + book.getTitle());
    }
}
