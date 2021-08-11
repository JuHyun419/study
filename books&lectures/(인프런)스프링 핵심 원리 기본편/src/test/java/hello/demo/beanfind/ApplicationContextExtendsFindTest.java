package hello.demo.beanfind;

import hello.demo.discount.DiscountPolicy;
import hello.demo.discount.FixDiscountPolicy;
import hello.demo.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;


public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(TestConfig.class);

//    @Test
//    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 중복 오류 발생")
//    void findBeanByParentTypeDuplicate() {
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
//        assertThrows(NoUniqueBeanDefinitionException.class,
//                () -> ac.getBean(DiscountPolicy.class));
//    }

    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBaenName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
