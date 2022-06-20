package com.funpay.common.springutil;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Leeko
 * @date 2022/2/16
 **/
@Component
public class SpringContextInstance implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextInstance.context = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return (T) context.getBean(clazz);
    }
}
