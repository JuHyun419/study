package hello.demo.singleton;

import hello.demo.AppConfig;
import hello.demo.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성 => 싱글톤
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 열어서 객체 인스턴스가 필요할 때 static 메서드를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성 불가능!
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출!!");
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {

        // private으로 생성자를 막아두었다. 컴파일 오류 발생
        // new SingletonService();

        // 1. 조회: 호출할 때 마다 같은 객체 반환
        SingletonService singletonService1 = SingletonService.getInstance();

        // 2. 조회: 호출할 때 마다 같은 객체 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같다!!
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // singletonService1 == singletonService2
        assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();;
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac
                = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회: 호출할 때 마다 같은 객체 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회: 호출할 때 마다 같은 객체 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같다!!
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }

}
