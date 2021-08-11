package hello.demo;

import hello.demo.discount.DiscountPolicy;
import hello.demo.discount.RateDiscountPolicy;
import hello.demo.member.MemberRepository;
import hello.demo.member.MemberService;
import hello.demo.member.MemberServiceImpl;
import hello.demo.member.MemoryMemberRepository;
import hello.demo.order.OrderService;
import hello.demo.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig는 애플리케이션의 실제 동작에 필요한 "구현객체"를 생성한다.
 */
@Configuration
public class AppConfig {

    // Extract Method: Ctrl + Alt + M

    @Bean
    public MemberService memberService() {
        // 1번
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    // 회원 저장소 역할(인터페이스)
    @Bean
    public MemberRepository memberRepository() {
        // 2번? 3번?
        System.out.println("call AppConfig.memberRepository");

        // 메모리 회원 저장소(구현체)
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        // 1번
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


    // 할인 정책 역할(인터페이스)
    @Bean
    public DiscountPolicy discountPolicy() {
        // 정액 할인 정책(구현체)
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
