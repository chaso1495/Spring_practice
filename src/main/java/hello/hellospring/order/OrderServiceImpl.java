package hello.hellospring.order;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.FixDiscountPolicy;
import hello.hellospring.discount.RateDiscountPolicy;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemberRepository;
import hello.hellospring.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final이 붙은 것들로 생성자를 만들어 준다. 2.생성자 주입 코드를 자동으로 만들어준다.
public class OrderServiceImpl implements OrderService {

    /*@Autowired // 1. 필드 주입
    private MemberRepository memberRepository;
    @Autowired
    private DiscountPolicy discountPolicy;*/

    private final MemberRepository memberRepository; // 필드에 final 키워드를 통해 반드시 초기화되어야 한다는 것을 확립한다.
    private final DiscountPolicy discountPolicy;
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 위 코드를 아래로 바꾸는 순간, 구현체의 내용이 바뀌므로 OCP에 위반된다.

    /*@Autowired // 2. 생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository; // 구현체의 종류를 알 필요가 없음. 오직 추상화에만 의존한다.
        this.discountPolicy = discountPolicy;
    }*/

    /*@Autowired // 3. 수정자 주입
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired // 3. 수정자 주입
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }*/

    /*@Autowired // 4. 일반 메서드 주입
    public void init(MemberRepository memberRepository, DiscountPolicy
            discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 단일 책임 원칙 이행
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
