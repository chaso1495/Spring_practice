package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 테스트 동작 전 실행
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 이를 통해 테스트 실행마다 새롭게 초기화 가능하면서, 아래 문제점이 해결된다.
    } // 이를 DI, 의존성 주입이라고 한다.

    @AfterEach
    public void afterEach(){ // 문제점: 서비스에도 테스트코드 내 repo가 있고, 여기에 또 다른 repo가 존재하기에 내용물이 달라질 수도 있다. 같은 것으로 테스트하는게 맞다.
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // 테스트 메서드명은 한글로 바꾸어도 된다.
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

        // 아래와 같은 예외처리를 위 한 줄로 깔끔하게 처리할 수 있다.
/*        try {
            memberService.join(member2);
            fail(); // 여기까지 오면 테스트 실패
        } catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."));
        } */

        // then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }

}