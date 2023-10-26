package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // final : 생성자를 통해서 할당
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자 생성
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberID, String itemName, int itemPrice) {
        // 회원 정보 조회
        Member member = memberRepository.findById(memberID);
        // 할인 정책에 회원을 넘김
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 최종 생성된 주문 반환
        return new Order(memberID, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
