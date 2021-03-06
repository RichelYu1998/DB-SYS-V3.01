package cn.tedu.sys.controller;

import cn.tedu.sys.entity.SysUserRoles;
import cn.tedu.sys.service.SysUserRolesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户与角色对应关系(SysUserRoles)表控制层
 *
 * @author makejava
 * @since 2020-07-10 16:38:24
 */
@RestController
@RequestMapping("sysUserRoles")
public class SysUserRolesController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserRolesService sysUserRolesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUserRoles selectOne(Integer id) {
        return this.sysUserRolesService.queryById(id);
    }

}