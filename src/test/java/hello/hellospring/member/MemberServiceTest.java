package hello.hellospring.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest { // 테스트 코드 작성
    MemberService memberService = new MemberServiceImpl();
    @Test
    void join() {
        // given 이런이런 게 주어진 상황에서
        Member member = new Member(1L, "memberA", Grade.VIP);
        // when 이렇게 했을 때

        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then 이렇게 된다!
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}