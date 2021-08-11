package hello.demo.discount;

import hello.demo.member.Grade;
import hello.demo.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 고정된 1000원 할인인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        return 0;
    }

    public boolean checkVipGrade(Grade grade) {
        if (grade == Grade.VIP) {
            return true;
        }
        return false;
    }
}
