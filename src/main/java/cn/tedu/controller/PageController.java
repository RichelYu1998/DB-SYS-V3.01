package cn.tedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")

public class PageController {
    @RequestMapping("doIndexUI")
    public String doIndexUI() {
        /*
         * 起始页面
         * */
        return "starter";
        /*
         * 分页处理
         * */
        //return "common/page";
    }
    /*
    * 日志管理页面
    * */
    @RequestMapping("log/log_list")
    public String doLogUI() {
        return "sys/log_list";
    }
    /*
    * 分页页面
    */
    @RequestMapping("doPageUI")
    public String doPageUI(){
        return "common/page";
    }
    @RequestMapping("{module}/{moduleUI}")
    public String doModuleUI(@PathVariable String moduleUI) {
        return "sys/"+moduleUI;
    }
}
