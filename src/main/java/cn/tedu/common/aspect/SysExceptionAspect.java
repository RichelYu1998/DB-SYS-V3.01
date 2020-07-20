package cn.tedu.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Aspect
@Component
public class SysExceptionAspect {
    @AfterThrowing(pointcut = "bean(*Service)",throwing = "e")
    public void doHandleException(JoinPoint jp,Throwable e){
        MethodSignature ms = (MethodSignature) jp.getSignature();
        log.error("{}'exception msg is {}",ms.getName(),e.getMessage());
    }
}
