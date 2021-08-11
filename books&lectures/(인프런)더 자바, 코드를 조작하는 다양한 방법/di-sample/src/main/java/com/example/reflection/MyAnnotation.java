package com.example.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface MyAnnotation {

    // default를 선언하지 않으면 어노테이션 선언할 때 값을 반드시 지정해줘야 함
    String value() default "JuHyun";

    int number() default 100;
}
