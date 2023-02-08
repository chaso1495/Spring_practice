package hello.hellospring;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.RateDiscountPolicy;
import hello.hellospring.member.MemberService;
import hello.hellospring.member.MemberServiceImpl;
import hello.hellospring.member.MemoryMemberRepository;
import hello.hellospring.order.OrderService;
import hello.hellospring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 어노테이션을 통해
public class AppConfig { // 앱 전체를 설정하고 구성한다. 객체의 생성과 연결을 담당한다.

    @Bean
    public MemberService memberService() { // 생성자 주입
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() { // 이렇게 정확한 역할을 드러내는 식으로 코드를 작성하면 메서드명으로 직관적인 역할 파악이 가능해진다.
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() { // 생성자 주입, 이를 받아낸 구현체는 오직 실행에만 집중하면 된다.
        System.out.println("call AppConfig.orderService");
        // return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
