package cn.tedu.sys.controller;

import cn.tedu.sys.entity.SysRoleMenus;
import cn.tedu.sys.service.SysRoleMenusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色与菜单对应关系(SysRoleMenus)表控制层
 *
 * @author makejava
 * @since 2020-07-10 16:38:20
 */
@RestController
@RequestMapping("sysRoleMenus")
public class SysRoleMenusController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleMenusService sysRoleMenusService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysRoleMenus selectOne(Integer id) {
        return this.sysRoleMenusService.queryById(id);
    }

}