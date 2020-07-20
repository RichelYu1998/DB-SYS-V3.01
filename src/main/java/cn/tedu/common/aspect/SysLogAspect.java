package cn.tedu.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
@Aspect
@Slf4j
@Component

public class SysLogAspect {
//    @Pointcut("bean(sysUsersServiceImpl)")
    @Pointcut("execution(* cn.tedu.sys.service.impl..*.*(..))")
    public void doLogPointCut() {
    }
    @Around("doLogPointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        log.info("method start {}", System.currentTimeMillis());
        try {
            Object result = jp.proceed();//最终会执行目标方法
            log.info("method end {}", System.currentTimeMillis());
            return result;
        } catch (Throwable e) {
            log.error("method error {},error msg is {}", System.currentTimeMillis(), e.getMessage());
            throw e;
        }
    }
}
/*
* Tips:
    * @Aspect 注解用于标识或者描述 AOP 中的切面类型，基于切面类型构建的对象用于
    为目标对象进行功能扩展或控制目标对象的执行。
    ▪ @Pointcut 注解用于描述切面中的方法，并定义切面中的切入点（基于特定表达式的
    方式进行描述），在本案例中切入点表达式用的是 bean 表达式，这个表达式以 bean
    开头，bean 括号中的内容为一个 spring 管理的某个 bean 对象的名字。
    ▪ @Around 注解用于描述切面中方法，这样的方法会被认为是一个环绕通知（核心业
    务方法执行之前和之后要执行的一个动作），@Aournd 注解内部 value 属性的值为
    一个切入点表达式或者是切入点表达式的一个引用(这个引用为一个@PointCut 注解
    描述的方法的方法名)。
    ▪ ProceedingJoinPoint 类为一个连接点类型，此类型的对象用于封装要执行的目标方
    法相关的一些信息。一般用于@Around 注解描述的方法参数。
* */