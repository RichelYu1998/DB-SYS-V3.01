package cn.tedu.common.aspect;


import cn.tedu.common.annotation.RequiredLog;
import cn.tedu.common.util.IPUtils;
import cn.tedu.sys.entity.SysLogs;
import cn.tedu.sys.service.SysLogsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
//@Slf4j
@Component

public class SysLogAspect {
    private static final Logger log =
            LoggerFactory.getLogger(SysLogAspect.class);
    /**
     * PointCut注解用于定义切入点，具体方式可以基于特定表达式进行实现。例如
     * 1)bean为一种切入点表达式类型
     * 2)sysUserServiceImpl 为spring容器中的一个bean的名字
     * 	这里的涵义是当sysUserServiceImpl对象中的任意方法执行时，都由本切面
     * 	对象的通知方法做功能增强。
     */
    //@Pointcut("bean(sysUsersService)")
    @Pointcut("bean(sysLogsService)")
    public void doLogPointCut() {}//此方法中不需要写任何代码
    private SysLogsService sysLogsService;
    /**由@Around注解描述的方法为一个环绕通知方法，我们可以在此方法内部
     * 手动调用目标方法(通过连接点对象ProceedingJoinPoint的proceed方法进行调用)
     * 环绕通知：此环绕通知使用的切入点为bean(sysUserServiceImpl)
     * 环绕通知特点：
     1)编写：
     a)方法的返回值为Object.
     b)方法参数为ProceedingJoinPoint类型.
     c)方法抛出异常为throwable.
     2)应用：
     a)目标方法执行之前或之后都可以进行功能拓展
     b)相对于其它通知优先级最高。
     @param jointPoint 为一个连接对象(封装了正在要执行的目标方法信息)
     @return 目标方法的执行结果
     */
    @Around(value="doLogPointCut()")
    public Object around(ProceedingJoinPoint jointPoint)throws Throwable{
        log.info("method start {}",System.currentTimeMillis());
            long startTime = System.currentTimeMillis();
        try {
            Object result=jointPoint.proceed();//最终会执行目标方法(sysUserServiceImpl对象中的方法)
            log.info("method end {}",System.currentTimeMillis());
            long endTime = System.currentTimeMillis();
            long totalTime=endTime-startTime;
            log.info("方法执行的总时长为："+totalTime);
            saveSysLog(jointPoint,totalTime);
            return result;
        }catch(Throwable e) {
            log.error("method error {},error msg is {}",
                    System.currentTimeMillis(),e.getMessage());
            throw e;
        }
    }

    private void saveSysLog(ProceedingJoinPoint point, long totalTime) throws NoSuchMethodException,SecurityException, JsonProcessingException {
        //1.获取日志信息
        MethodSignature ms = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        String className = targetClass.getName();
        //获取接口声明的方法
        String methodName = ms.getMethod().getName();
        Class<?>[] parameterTypes = ms.getMethod().getParameterTypes();
        //获取目标对象方法(AOP 版本不同,可能获取方法对象方式也不同)
        Method targetMethod = targetClass.getDeclaredMethod(methodName, parameterTypes);
        //获取用户名,学完 shiro 再进行自定义实现,没有就先给固定值
        String username="admin";
        //获取方法参数
        Object[] paramsObj=point.getArgs();
        System.out.println("paramsObj="+paramsObj);
        //将参数转换为字符串
        String params = new ObjectMapper().writeValueAsString(paramsObj);
        //2.封装日志信息
        SysLogs log = new SysLogs();
        log.setUsername("username");//登陆的用户
        //假如目标方法对象上有注解,我们获取注解定义的操作值
        targetMethod.getDeclaredAnnotation(RequiredLog.class);
        log.setMethod(className+"."+methodName);//className.methodName()
        log.setParams(params);//method params
        log.setIp(IPUtils.getIpAddr());//ip 地址
        log.setTime(totalTime);//
        log.setCreatedTime(new Date());
        //3.保存日志信息
        sysLogsService.saveObject(log);
    }
}