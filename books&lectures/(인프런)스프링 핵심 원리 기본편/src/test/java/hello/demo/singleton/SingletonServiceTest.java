package hello.demo.singleton;

import static org.junit.jupiter.api.Assertions.*;

class SingletonServiceTest {

    // private으로 생성자를 막아두었다. 컴파일 오류 발생
    // new SingletonService();

    // 1. 조회: 호출할 때 마다 같은 객체 반환
    SingletonService singletonService1 = SingletonService.getInstance();

    // 2. 조회: 호출할 때 마다 같은 객체 반환
    SingletonService singletonService2 = SingletonService.getInstance();

    // 참조값이 같다!!

}
