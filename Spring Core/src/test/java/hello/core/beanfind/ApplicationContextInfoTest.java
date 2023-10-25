package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        // Bean으로 정의된 이름을 모두 등록
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // Type을 지정하지 않았기 때문에 Object를 꺼내줌
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        // Bean으로 정의된 이름을 모두 등록
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // Bean 하나 하나에 대한 메타 데이터 정보
            // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // 스프링 내부에서 등록한 Bean이 아닌 내가 애플리케이션을 개발하기 위해 등록한 Bean이나 외부 라이브러리
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                // Type을 지정하지 않았기 때문에 Object를 꺼내줌
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
