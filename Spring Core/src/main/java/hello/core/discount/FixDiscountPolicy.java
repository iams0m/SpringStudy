package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    
    private int discountFixAmount = 1000; // 1000원 할인
    
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // 등급이 VIP이면
            return discountFixAmount; // 1000원 할인
        } else { // 그렇지 않으면
            return 0; // 할인 없음
        }
    }
}
