package cn.tedu.controller;

import cn.tedu.entity.SysMenus;
import cn.tedu.service.SysMenusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 资源管理(SysMenus)表控制层
 *
 * @author makejava
 * @since 2020-07-10 16:38:16
 */
@RestController
@RequestMapping("sysMenus")
public class SysMenusController {
    /**
     * 服务对象
     */
    @Resource
    private SysMenusService sysMenusService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysMenus selectOne(Integer id) {
        return this.sysMenusService.queryById(id);
    }

}