package hello.hellospring.discount;

import hello.hellospring.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price); // F2로 에러 접근, option+
    // 엔터로 Import 가능
}
