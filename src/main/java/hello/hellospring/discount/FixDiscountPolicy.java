package hello.hellospring.discount;

import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){ // enum 타입은 == 으로 비교
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
