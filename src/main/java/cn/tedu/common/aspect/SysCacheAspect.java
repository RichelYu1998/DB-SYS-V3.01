package cn.tedu.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysCacheAspect {
    @Pointcut("@annotation(cn.tedu.common.annotation.RequiredCache)")
    public void doCache(){}
    @Around("doCache()")
    public Object around(ProceedingJoinPoint jp)throws Throwable{
        System.out.println("Get data from cache");
        Object obj = jp.proceed();
        System.out.println("Put data to cache");
        return obj;
    }
}
