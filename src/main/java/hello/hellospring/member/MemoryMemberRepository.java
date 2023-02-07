package hello.hellospring.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component // 이를 통해 등록된 빈의 이름은 앞이 소문자인 memoryMemberRepository가 된다.
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 이슈때문에 컨커런트해쉬맵을 사용한다.

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
