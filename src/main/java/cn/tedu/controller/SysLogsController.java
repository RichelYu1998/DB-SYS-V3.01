package cn.tedu.controller;

import cn.tedu.entity.SysLogs;
import cn.tedu.service.SysLogsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统日志(SysLogs)表控制层
 *
 * @author makejava
 * @since 2020-07-10 14:34:29
 */
@RestController
@RequestMapping("sysLogs")
public class SysLogsController {
    /**
     * 服务对象
     */
    @Resource
    private SysLogsService sysLogsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysLogs selectOne(Long id) {
        return this.sysLogsService.queryById(id);
    }

}