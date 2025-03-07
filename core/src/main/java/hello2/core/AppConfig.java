package hello2.core;

import hello2.core.discount.DiscountPolicy;
import hello2.core.discount.FixDiscountPoilcy;
import hello2.core.discount.RateDiscountPolicy;
import hello2.core.member.MemberRepository;
import hello2.core.member.MemberService;
import hello2.core.member.MemberServiceImpl;
import hello2.core.member.MemoryMemberRepository;
import hello2.core.order.OrderService;
import hello2.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { //역할을 배정하는 역할을 함

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPoilcy();
        return new RateDiscountPolicy();
    }
}
