package com.funpay.management.configuration.dynamicdatasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Leeko
 * @date 2022/1/20
 **/
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.funpay.management.configuration.dynamicdatasource.DS)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void beforeSwitchDS(JoinPoint jp) {
        try {
            MethodSignature signature = (MethodSignature) jp.getSignature();
            Method method = signature.getMethod();
            DS annotation = method.getAnnotation(DS.class);
            DataSourceConfig.DataSourceContextHolder.setDb(annotation.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("当前数据源为" + DataSourceConfig.DataSourceContextHolder.getDb());
    }

    @After("pointCut()")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceConfig.DataSourceContextHolder.clearDb();
    }
}
