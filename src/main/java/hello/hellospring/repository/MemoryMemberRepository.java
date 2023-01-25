package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 임시 저장소 store
    private static long sequence = 0L;

    @Override
    public Member save(Member member) { // 멤버 저장소에 저장하기
        member.setId(++sequence); // Id 세팅
        store.put(member.getId(), member); // 스토어에 멤버 키-값 형태로 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { // 아이디 찾기 구현
        return Optional.ofNullable(store.get(id)); // null일 수도 있을 때 Optional로 감싸는 반환 방법
    }

    @Override
    public Optional<Member> findByName(String name) { // 이름 찾기 구현
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        // stream을 통해 반복적으로 순회하며 filter(조건 람다식)의 조건에 일치하는 경우만 필터링이 되어 findAny, 즉 하나라도 찾으면 반환이 된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() { // 데이터 소멸
        store.clear();
    }
}
