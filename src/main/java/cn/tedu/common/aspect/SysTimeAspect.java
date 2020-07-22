package cn.tedu.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Order(2)
@Component
@Aspect
public class SysTimeAspect {
    @Pointcut("bean(sysUsersService)")
    public void doTime(){}
    @Before("doTime()")
    public void doBefore(JoinPoint jp){
        System.out.println("time doBefore()");
    }
    @After("doTime()")
    public void doAfter(JoinPoint jp){
        System.out.println("time doAfter()");
    }
    /**核心业务正常结束时执行* 说明：假如有 after，先执行 after,再执行
     returning*/
    @AfterReturning("doTime()")
    public void AfterReturning(JoinPoint jp){
        System.out.println("time AfterReturning()");
    }
    /**核心业务出现异常时执行说明：假如有 after，先执行 after,再执行
     Throwing*/
    @AfterThrowing("doTime()")
    public void AfterThrowing(JoinPoint jp){
        System.out.println("time AfterThrowing()");
    }
    @Around("doTime()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("doAround.before");
        try {
            Object obj = jp.proceed();
            System.out.println("doAround.after");
            return obj;
        }catch (Throwable e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
