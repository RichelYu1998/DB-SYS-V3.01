package cn.tedu.sys.controller;

import cn.tedu.common.vo.JsonResult;
import cn.tedu.sys.entity.SysUsers;
import cn.tedu.sys.service.SysUsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}