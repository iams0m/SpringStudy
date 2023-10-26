package hello.core.scan.Filter;

import java.lang.annotation.*;

// MyIncludeComponent가 붙으면, ComponentScan에 추가
@Target(ElementType.TYPE) // type : 클래스 레벨에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
