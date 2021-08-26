package com.juhyun.springaop.proxy;

/* 프록시 */
public class BookServiceProxy implements BookService {

    // 프록시는 리얼 서브젝트를 가지고 있어야 함
    private final RealBookService realBookService;

    public BookServiceProxy(RealBookService realBookService) {
        this.realBookService = realBookService;
    }

    // 프록시에서 부가기능을 추가
    @Override
    public void rent(Book book) {
        System.out.println("트랜잭션 처리");
        realBookService.rent(book); // 프록시 -> 리얼 서브젝트
        System.out.println("로깅");
    }
}
