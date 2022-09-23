package com.cjj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author cjj
 * @date 2022/9/23 13:55
 * 定义接口是否可以重复提交注解，默认为false，不能重复提交
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatRequest {
    boolean value() default false;
}
