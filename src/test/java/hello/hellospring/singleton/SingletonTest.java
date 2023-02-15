package hello.hellospring.singleton;

import hello.hellospring.AppConfig;
import hello.hellospring.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1.조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 위와 같은 컨테이너는 요청할 때마다 새 객체를 생성한다. 이는 메모리 관리 측면에서 효율적이지 못하다.
        // 해당 객체를 단 하나만 생성하게 하고 이를 공유하도록 하면 된다 -> 싱글톤 패턴

        // 참조값이 다른 것을 확인
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){ // 스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용할 수 있다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 1.조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 위와 같은 컨테이너는 요청할 때마다 새 객체를 생성한다. 이는 메모리 관리 측면에서 효율적이지 못하다.
        // 해당 객체를 단 하나만 생성하게 하고 이를 공유하도록 하면 된다 -> 싱글톤 패턴

        // 참조값이 다른 것을 확인
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
