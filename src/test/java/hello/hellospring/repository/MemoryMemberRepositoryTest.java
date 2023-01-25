package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest { // 테스트 코드는 작성 순서랑 상관없이 실행된다. 순서에 의존되게 작성하면 안 된다.
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach // 각각의 테스트가 한 번 실행될 때마다 실행되는 메서드, 사용된 테스트 데이터는 지워주어야 문제가 발생하지 않는다.
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // optional에서 값을 꺼낼 때는 get을 활용한다.
        assertThat(member).isEqualTo(result); // 둘이 똑같은지 확인하는 assert 메서드
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
// 테스트 코드는 코드를 검증하는 과정에서 코드의 틀 역할을 한다. 이를 앞세우는 것을 테스트 주도 개발이라고 부른다. (테스트 코드 후 구현 코드 작성)
// 테스트는 정말 다양할 수 있다. 테스트 없이 개발하는 것은 사실상 불가능하기에 깊이있는 공부가 필요하다.