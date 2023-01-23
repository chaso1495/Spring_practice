package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 서비스는 도메인과 리포를 활용하여 실제 비즈니스 로직을 작성하는 부분이다.

// 서비스는 비즈니스를 처리하기에 비즈니스에 의존적으로 설계한다.
public class MemberService { // 쉬프트 command T로 테스트 메서드를 작성할 수 있다.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 불가능하다는 조건을 추가하였음.
        // memberRepository.findByName(member.getName()); 에서 option+command+v로 반환형으로 아래처럼 바꿀 수 있다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 중복 회원 검증 메서드, 마찬가지로 테스트케이스를 작성하는 습관이 들이면 좋다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
     * 전체 회원 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}

