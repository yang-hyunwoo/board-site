package com.board.boardsite.support;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class ParameterAop {

    @Pointcut("execution(* com.board.boardsite.controller..*.*(..))")
    private void cut() {

    }


    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();    //joinPoint 안의 argument값을 가져옴
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod(); //메소드의 이름
        for(Object obj : args) {
            if(obj!=null) {
                log.info("before controller : {} , method : {} , type : {} , value : {}", method, method.getName(), obj.getClass().getSimpleName(), obj);
            }
        }
    }

    @AfterReturning(value = "cut()" , returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint , Object returnObj){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod(); //메소드의 이름
        var a = returnObj;
        log.info("afterReturn : method : {} , returnObj : {}",method.getName(),returnObj);
    }

}
