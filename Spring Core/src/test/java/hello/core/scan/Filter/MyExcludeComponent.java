package hello.core.scan.Filter;

import java.lang.annotation.*;

// MyExcludeComponent가 붙으면, ComponentScan에서 제외
@Target(ElementType.TYPE) // type : 클래스 레벨에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
