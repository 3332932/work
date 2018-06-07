package com.cn.springboot.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
public class AspectClass {

    private HttpServletRequest request = null;

    @Before("execution(* com.cn.springboot.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method= methodSignature.getMethod();
        System.out.println("开始 "+method.getName());
    }

    @After("execution(* com.cn.springboot.controller.*.*(..))")
    public void after(JoinPoint joinPoint){
        request=getHttpServletRequest();
        Map map = request.getParameterMap();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method= methodSignature.getMethod();
        System.out.println("结束 "+method.getName()+"sdfdfdfe");
    }

    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}
