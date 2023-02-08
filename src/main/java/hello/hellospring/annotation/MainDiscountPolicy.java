package hello.hellospring.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
// @Qualifier("mainDiscountPolicy")는 컴파일시 타입 체크가 안된다. 위와 같은 애노테이션을 만들어서 문제를 해결할 수 있다.