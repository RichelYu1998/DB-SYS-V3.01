package cn.tedu.common.web;

import cn.tedu.common.vo.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    //JDK 中的自带的日志 API
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult doHandleRuntimeException(RuntimeException e){
        e.printStackTrace();//也可以写日志 异常信息
        return new JsonResult(e);
    }
}
