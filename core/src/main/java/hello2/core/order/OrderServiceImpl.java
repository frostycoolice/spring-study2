package hello2.core.order;

import hello2.core.discount.DiscountPolicy;
import hello2.core.discount.FixDiscountPoilcy;
import hello2.core.discount.RateDiscountPolicy;
import hello2.core.member.Member;
import hello2.core.member.MemberRepository;
import hello2.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //private final DiscountPolicy discountPolicy = new FixDiscountPoilcy(); << DIP, OCP 위반 한 방식.
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); << 동일

    //private DiscountPolicy discountPolicy; // DIP는 준수하였지만 코드는 안돌아감. AppConfig를 통하여 해결


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
