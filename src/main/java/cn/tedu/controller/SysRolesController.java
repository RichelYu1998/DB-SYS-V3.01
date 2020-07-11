package cn.tedu.controller;

import cn.tedu.entity.SysRoles;
import cn.tedu.service.SysRolesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色(SysRoles)表控制层
 *
 * @author makejava
 * @since 2020-07-10 16:38:22
 */
@RestController
@RequestMapping("/role/")
public class SysRolesController {
    /**
     * 服务对象
     */
    @Resource
    private SysRolesService sysRolesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysRoles selectOne(Long id) {
        return this.sysRolesService.queryById(id);
    }

}