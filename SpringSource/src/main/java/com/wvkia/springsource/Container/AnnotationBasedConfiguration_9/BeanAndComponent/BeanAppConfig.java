package com.wvkia.springsource.Container.AnnotationBasedConfiguration_9.BeanAndComponent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Bean是方法级别注解，可以用到@Component和@Configuraton这两个类注解上，用来实现Bean的声明
 * 相当于
 * <beans>
 *     <bean id="transferService" class="com.acme.TransferServiceImpl"/>
 * </beans>
 */
@Configuration
@Import(ImportConfig.class)
public class BeanAppConfig {
    @Bean(initMethod = "init"  ,destroyMethod = "destroy")
    public Bean0 getBean0(){
        return new Bean0();
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanAppConfig.class);
        Bean0 bean0 = context.getBean(Bean0.class);
        System.out.println(bean0);
        Bean1 bean1 = context.getBean(Bean1.class);
        System.out.println(bean1);
    }
}
