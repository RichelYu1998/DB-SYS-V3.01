package cn.tedu.controller;

import cn.tedu.entity.SysUserRoles;
import cn.tedu.service.SysUserRolesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户与角色对应关系(SysUserRoles)表控制层
 *
 * @author makejava
 * @since 2020-07-10 14:34:37
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