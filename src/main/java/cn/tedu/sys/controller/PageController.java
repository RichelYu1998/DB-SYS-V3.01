package cn.tedu.sys.controller;

import cn.tedu.common.util.ShiroUtils;

import cn.tedu.common.vo.SysUserMenuVo;
import cn.tedu.sys.dao.SysMenusDao;
import cn.tedu.sys.entity.SysUsers;
import cn.tedu.sys.service.SysMenusService;
import cn.tedu.sys.service.SysUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/")

public class PageController {
    @Resource
    private SysMenusService sysMenusService;
    @RequestMapping("doIndexUI")
    public String doIndexUI(Model model) {
        SysUsers user = ShiroUtils.getUser();
        model.addAttribute("username",user.getUsername());
        List<SysUserMenuVo> userMenus = sysMenusService.findUserMenusByUserId(user.getId());
        model.addAttribute("userMenus",userMenus);
        /*
         * 起始页面
         * */
        return "starter";
    }

    /*
     * 分页查询
     * */
    @RequestMapping("doPageUI")
    public String doPageUI() {
        return "common/page";
    }

    /*
     * 菜单列表
     * */
    /*@RequestMapping("menu/menu_list")
    public String doMenuUI(){
        return "sys/menu_list";
    }*/
    /*
     * 日志管理页面
     * */
   /* @RequestMapping("log/log_list")
    public String doLogUI() {
        return "sys/log_list";
    }*/
    //rest风格(一种架构风格)的url，其语法结构{变量名}/{变量}
    //PathVariable注解用于修饰方法参数，可以从rest风格的url中取和参数名相同的值
    @RequestMapping("{module}/{moduleUI}")
    public String doModuleUI(@PathVariable String moduleUI) {
        return "sys/" + moduleUI;
    }
    /*
    * 登录验证
    * */
    @RequestMapping("doLoginUI")
    public String doLoginUI(){
        return "login";
    }
}
