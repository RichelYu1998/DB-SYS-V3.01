package cn.tedu.common.aspect;

import cn.tedu.common.annotation.RemovedCache;
import cn.tedu.common.annotation.RequiredCache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class SysCacheAspect {
    //借助此对象缓存从数据库查询到的数据
    private Map<String,Object> cache=new ConcurrentHashMap<>();


    @Pointcut("bean(*Service)")
    public void removeCache() {}

    @Pointcut("bean(*Service)")
    public void requiredCache() {}
    /**
     * 此通知何时执行？
     * @param jp
     * @throws Exception
     */
    @AfterReturning("@annotation(cn.tedu.common.annotation.RemovedCache)")
    public void doAfterReturning(JoinPoint jp)throws Exception {
        //获取目标方法
        Method targetMethod = doGetTargetMethod(jp);
        //获取目标方法的注解
        RemovedCache removedCache=targetMethod.getAnnotation(RemovedCache.class);
        //获取注解上定义的key
        String key=removedCache.key();
        cache.remove(key);
        //cache.clear();
    }

    private Method doGetTargetMethod(JoinPoint jp) throws NoSuchMethodException {
        //通过连接点获取方法签名(方法签名中包含你要执行的目标方法信息)
        MethodSignature ms=(MethodSignature)jp.getSignature();
        //获取目标类的字节码对象
        Class<?> targetCls=jp.getTarget().getClass();
        //获取目标方法
        Method targetMethod=
                targetCls.getDeclaredMethod(ms.getName(),ms.getParameterTypes());
        return targetMethod;
    }
    //ProceedingJoinPoint
    @Around("@annotation(cn.tedu.common.annotation.RequiredCache)")
    public Object around(ProceedingJoinPoint jp)throws Throwable{
        //0.获取目标方法以及目标方法上的RequiredCache注解
        Method targetMethod=doGetTargetMethod(jp);
        RequiredCache requiredCache=targetMethod.getAnnotation(RequiredCache.class);
        //1.从 cache获取数据
        String key=requiredCache.key();
        Object result=cache.get(key);//将来的key会基于方法参数生成。
        if(result!=null)return result;
        //2.cache中没有则查询数据库
        result=jp.proceed();
        //3.将查询到的数据存储到cache
        cache.put(key, result);
        //4.返回查询结果
        return result;
    }
}
