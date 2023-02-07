package hello.hellospring;

import hello.hellospring.member.MemberRepository;
import hello.hellospring.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackages = "hello.hellospring.member", // member 패키지만 컴포넌트 스캔의 대상이 된다는 의미, 지정하지 않으면 하위 패키지 전체를 찾아본다.
        // 일반적인 상황은 패키지 위치를 따로 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository") // MemoryMemberRepository를 Component화한 빈과 충돌한다. 이 경우 수동 빈이 우선권을 가진다.
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    // 그러나 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.
}
