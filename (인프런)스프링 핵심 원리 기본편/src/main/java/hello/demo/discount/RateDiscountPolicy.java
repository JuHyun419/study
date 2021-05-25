package hello.demo.discount;

import hello.demo.member.Grade;
import hello.demo.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            // 10% 할인율이 맞는지 헷갈림 => 테스트 코드 작성(Ctrl + Shift + T)
            return price * discountPercent / 100;
        }
        return 0;
    }
}
