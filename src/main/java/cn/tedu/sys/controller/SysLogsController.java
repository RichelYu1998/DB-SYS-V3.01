package cn.tedu.sys.controller;

import cn.tedu.common.vo.JsonResult;
import cn.tedu.sys.entity.PageObject;
import cn.tedu.sys.entity.SysLogs;
import cn.tedu.sys.service.SysLogsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统日志(SysLogs)表控制层
 *
 * @author makejava
 * @since 2020-07-10 16:38:11
 */
@RestController
@RequestMapping("/log/")
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

    /*
     * 分页请求
     * */
    @RequestMapping("doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
        PageObject<SysLogs> pageObjects = sysLogsService.findPageObjects(username, pageCurrent);
        return new JsonResult(pageObjects);
    }

    @RequestMapping("doDeleteObjects")
    @ResponseBody
    public JsonResult doDeleteObjects(Integer... ids){
        sysLogsService.deleteObjects(ids);
        return new JsonResult("delete ok");
    }
}