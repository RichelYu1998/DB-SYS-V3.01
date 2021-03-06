package cn.tedu.sys.controller;

import cn.tedu.common.vo.JsonResult;
import cn.tedu.sys.entity.SysUsers;
import cn.tedu.sys.service.SysUsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 系统用户(SysUsers)表控制层
 *
 * @author makejava
 * @since 2020-07-10 16:38:25
 */
@RestController
@RequestMapping("/user/")
public class SysUsersController {
    /**
     * 服务对象
     */
    @Resource
    private SysUsersService sysUsersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUsers selectOne(Integer id) {
        return this.sysUsersService.queryById(id);
    }

    /*
    *菜单查询
    * */
    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(String username,Integer pageCurrent){
        return new JsonResult(sysUsersService.findPageObjects(username, pageCurrent));
    }
    @RequestMapping("doValidById")
    public JsonResult doValidById(Integer id, Integer valid){
        sysUsersService.validById(id, valid);
        return new JsonResult("update ok");
    }
    /*
    * 基于用户 ID 查询用户
    * */
    @RequestMapping("doFindObjectById")
    public JsonResult doFindObjectById(Integer id){
        Map<String, Object> map = sysUsersService.findObjectById(id);
        return new JsonResult(map);
    }
    /*
    * 更新角色
    * */
    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(
            SysUsers entity,Integer[] roleIds){
        sysUsersService.updateObject(entity,roleIds);
        return new JsonResult("update ok");

    }
    /*
    * 保存用户数据
    * */
    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysUsers entity, Integer[] roleIds){
        sysUsersService.saveObject(entity,roleIds);
        return new JsonResult("save ok");
    }
    /*
    * 密码修改
    * */
    @RequestMapping("doUpdatePassword")
    public JsonResult doUpdatePassword(String pwd, String newPwd, String cfgPwd){
        sysUsersService.updatePassword(pwd, newPwd, cfgPwd);
        return new JsonResult("update ok");
    }
    /*
    * 处理登陆
    * */
    @RequestMapping("doLogin")
    public JsonResult doLogin(boolean isRememberMe, String username, String password){
        UsernamePasswordToken token=
                new UsernamePasswordToken(username, password);
        if(isRememberMe) {
            token.setRememberMe(true);
        }
    //2.提交用户信息
        Subject subject=SecurityUtils.getSubject();
        subject.login(token);//token 会提交给 securityManager
        return new JsonResult("login ok");
    }
}