package hello.hellospring.discount;

import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10; // 할인 비율
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100; // 실제 이런 금액과 관련된 로직은 다양한 경계값으로 테스트해봐야 한다. 오동작이 나오면 당연히 안 되니까.
        } else {
            return 0;
        }
    }
}
