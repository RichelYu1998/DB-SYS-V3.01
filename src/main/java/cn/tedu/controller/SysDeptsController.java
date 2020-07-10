package cn.tedu.controller;

import cn.tedu.entity.SysDepts;
import cn.tedu.service.SysDeptsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 部门管理(SysDepts)表控制层
 *
 * @author makejava
 * @since 2020-07-10 14:34:24
 */
@RestController
@RequestMapping("sysDepts")
public class SysDeptsController {
    /**
     * 服务对象
     */
    @Resource
    private SysDeptsService sysDeptsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysDepts selectOne(Integer id) {
        return this.sysDeptsService.queryById(id);
    }

}