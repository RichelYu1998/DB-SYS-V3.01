package cn.tedu.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SysTimeAspect {
    @Pointcut("bean(sysUsersServiceImpl)")
    public void doTime(){}
    @Before("doTime()")
    public void doBefore(JoinPoint jp){
        System.out.println("time doBefore()");
    }
    @After("doTime()")
    public void doAfter(){
        System.out.println("time doAfter()");
    }
    /**核心业务正常结束时执行* 说明：假如有 after，先执行 after,再执行
     returning*/
    @AfterReturning("doTime()")
    public void doAfterReturning(){
        System.out.println("time doAfterReturning");
    }
    /**核心业务出现异常时执行说明：假如有 after，先执行 after,再执行
     Throwing*/
    @AfterThrowing("doTime()")
    public void doAfterThrowing(){
        System.out.println("time doAfterThrowing");
    }
    @Around("doTime()")
    public Object doAround(ProceedingJoinPoint jp)
            throws Throwable{
        System.out.println("doAround.before");
        try{
            Object obj=jp.proceed();
            System.out.println("doAround.after");
            return obj;
        }catch(Throwable e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
