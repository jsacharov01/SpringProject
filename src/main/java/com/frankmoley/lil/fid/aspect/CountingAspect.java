package com.frankmoley.lil.fid.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class CountingAspect {

    private static final Map<String, Integer> counter = new HashMap<String, Integer>();

    @Pointcut("@annotation(Countable)")
    public void executeCounting(){

    }

    @Before("executeCounting()")
    public void countMethodCall(JoinPoint joinPoint){

        String methodName = joinPoint.getSignature().getName();
        int count = counter.getOrDefault(methodName, 0);
        counter.put(methodName, count+1);

        StringBuilder currentStatus = new StringBuilder();
        counter.forEach((key, value) ->{
            currentStatus.append("Method: ").append(key).append(", Count: ").append(value).append("\n");
        });

        System.out.println(currentStatus.toString());
    }
}
