package cn.tedu.common.web;

import cn.tedu.common.exception.ServiceException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

public class TimeAccessInterceptor implements HandlerInterceptor {
    public boolean HandlerInterceptor(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        System.out.println("preHandler()");
        //获取 java 中的日历对象
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 6);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        long start = c.getTimeInMillis();
        c.set(Calendar.HOUR_OF_DAY, 23);
        long end=c.getTimeInMillis();
        long cTime=System.currentTimeMillis();
        if(cTime<start||cTime>end)
            throw new ServiceException("不在访问时间之内");
        return true;
    }
}
