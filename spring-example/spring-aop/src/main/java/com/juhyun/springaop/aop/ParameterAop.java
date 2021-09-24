package com.juhyun.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    /*
    com.juhyun.springaop.aop.controller 패키지 하위에 있는 메소드가 실행되기 전
     -> @Before 어노테이션이 선언된 before() 메소드가 실행
     -> 정상 실행되고 리턴되면 @AfterReturning 어노테이션이 선언된 afterReturn() 메소드에서 결과를 확인할 수 있음
     */

    // controller 패키지의 하위 메소드를 모두 Aspect로 설정
    @Pointcut("execution(* com.juhyun.springaop.aop.controller..*.*(..))")
    private void cut() {
    }

    // cut() 메소드가 실행되는 지점의 이전에 before() 메소드 수행
    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("--------------- @Before ---------------");
        System.out.println(joinPoint.toString());
        System.out.println(joinPoint.toShortString());
        System.out.println(joinPoint.toLongString());

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("method: " + method.getName());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("type: " + arg.getClass().getSimpleName());
            System.out.println("value: " + arg);
        }
    }

    // 반환값 확인
//    @AfterReturning(value = "cut()", returning = "object")
//    public void afterReturn(JoinPoint joinPoint, Object object) {
//        System.out.println("--------------- @After ---------------");
//        System.out.println(object);
//    }

}
