package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefullService statefullService1 = ac.getBean(StatefullService.class);
        StatefullService statefullService2 = ac.getBean(StatefullService.class);

        //ThreadA: A사용자 10000원 주문
        int userPrice1 = statefullService1.order("userA", 10000);
        //ThreadA: B사용자 20000원 주문
        int userPrice2 = statefullService2.order("userB", 20000);

        //ThreadA: A사용자 주문 금액 조회
//        int price = statefullService1.getPrice();

        System.out.println("price = " + userPrice1);

//        Assertions.assertThat(statefullService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefullService statefullService() {
            return new StatefullService();
        }
    }

}