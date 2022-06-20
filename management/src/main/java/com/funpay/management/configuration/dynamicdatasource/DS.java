package com.funpay.management.configuration.dynamicdatasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Leeko
 * @date 2022/1/20
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DS {
    String value() default DataSourceConfig.DS_LOC;
}
