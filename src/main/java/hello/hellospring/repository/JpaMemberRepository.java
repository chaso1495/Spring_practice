package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // jpa가 insert 쿼리 다 만들어서 DB에 삽입하고 id까지 부여함.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // id가 PK인 상황에서
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny(); // 리스트 중 하나만 찾아야 함 (중복이 없어야 함)
    }

    @Override
    public List<Member> findAll() { // entity 대상으로 쿼리를 날린다. m은 객체이다. 즉 객체 자체를 select한다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
