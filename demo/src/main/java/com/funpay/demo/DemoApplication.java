package com.funpay.demo;

import com.funpay.exception.handler.UnifiedExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.funpay.exception", "com.funpay.demo"})
@MapperScan("com.funpay.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        UnifiedExceptionHandler bean = run.getBean(UnifiedExceptionHandler.class);
        System.out.println(bean);
    }

}
