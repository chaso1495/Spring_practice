package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 시스템과 관련된 테스트 코드임을 명시
@Transactional // afterEach 대신 사용, 테스트가 종료되면 해당 DB에 가한 action이 롤백된다. 즉 같은 코드로 테스트를 반복할 수 있게 된다.
class MemberServiceIntegrationTest { // 스프링 없이 테스트하는 게 좋은 테스트일 확률이 높다. 스프링 통합 테스트를 되도록이면 최소화하고 유닛 테스트를 극대화하자.
    // 작성 코드가 3, 테스트 코드가 7, 테스트 코드를 잘 짜는 것이 정말 중요하다.
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() { // 이제 직접 스프링을 실행한 뒤 테스트에 돌입한다. 테스트가 종료되면 스프링은 아웃된다.
        // given (이런 상황이 주어졌고)
        Member member = new Member();
        member.setName("hello");

        // when (이 때 테스트를 실행하면)
        Long saveId = memberService.join(member);

        // then (이런 검증 결과가 나와야 함)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 반환 가능
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}