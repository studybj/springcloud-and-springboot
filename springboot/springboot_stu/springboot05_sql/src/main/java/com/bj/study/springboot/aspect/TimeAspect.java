package com.bj.study.springboot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.bj.study.springboot.controller.FastJsonController..*(..))")
    public Object method(ProceedingJoinPoint point) throws Throwable{
        System.out.println("=====Aspect处理=======");
        Object[] args = point.getArgs();
        for (Object arg : args) {
            System.out.println("参数为:" + arg);
        }
        long start = System.currentTimeMillis();
        Object object = point.proceed();
        System.out.println("Aspect 耗时:" + (System.currentTimeMillis() - start));
        return object;
    }
}
