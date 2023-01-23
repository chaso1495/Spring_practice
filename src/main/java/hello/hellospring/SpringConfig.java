package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// @Service, @Repository 등의 Annotation을 붙이지 않고 자바 코드로 스프링 빈을 직접 등록하는 방법
@Configuration // 스프링이 뜰 때 Configuration을 읽고 이 로직을 호출해 스프링 빈에 해당 서비스를 등록해둔다.
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource); // 스프링은 이와 같이 DI를 첨가한 다형성(인터페이스 상속) 활용에 최적화되어 있다.
        return new JdbcTemplateMemberRepository(dataSource);
    }

}