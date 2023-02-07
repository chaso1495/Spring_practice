package hello.hellospring.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자를 통해서 구현체의 종류를 결정, 이렇게 오직 추상화에만 의존하는 코드 작성가능
        this.memberRepository = memberRepository; // 이 친구는 구현체의 종류를 알 필요가 없음. [생성자 주입]
    } // 이를 통해 의존관계에 대한 고민을 외부에 맡기고, 관심사가 철저히 분리된 채 실행에만 집중하면 된다.

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
