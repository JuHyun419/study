package com.juhyun.springaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* com.juhyun.springaop.aop.controller..*.*(..))")
    private void cut() {
    }

    @Pointcut("@annotation(com.juhyun.springaop.aop.annotation.Timer)")
    private void enableTimer() { }

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("--------------- @Around ---------------");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 타겟 오브젝트의 메서드 실행(controller 패키지 하위 클래스들의 메소드)
        Object result = joinPoint.proceed();
        System.out.println("result: " + result);

        stopWatch.stop();

        System.out.println("Total Time: " + stopWatch.getTotalTimeSeconds());
    }

}
