package cn.tedu.common.web;

import cn.tedu.common.vo.JsonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    //JDK 中的自带的日志 API
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult doHandleRuntimeException(RuntimeException e) {
        e.printStackTrace();//也可以写日志 异常信息
        return new JsonResult(e);
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public JsonResult doHandleShiroException(ShiroException e) {
        JsonResult r = new JsonResult();
        r.setState(0);
        if(e instanceof UnknownAccountException){
            r.setMessage("账户不存在");
        }else if(e instanceof LockedAccountException){
            r.setMessage("账户已被禁用");
        }else if(e instanceof IncorrectCredentialsException){
            r.setMessage("密码不正确");
        }else if(e instanceof AuthenticationException){
            r.setMessage("没有此操作权限");
        }else {
            r.setMessage("系统维护中");
        }
        e.printStackTrace();
        return r;
    }
}
