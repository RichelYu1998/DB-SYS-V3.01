package cn.tedu.sys.controller;

import cn.tedu.common.vo.JsonResult;
import cn.tedu.sys.entity.SysDepts;
import cn.tedu.sys.service.SysDeptsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 部门管理(SysDepts)表控制层
 *
 * @author makejava
 * @since 2020-07-10 16:38:09
 */
@RestController
@RequestMapping("/dept/")
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
    @RequestMapping("doFindObjects")
    public JsonResult doFindObjects() {
        return new JsonResult(sysDeptsService.findObjects());
    }
    @RequestMapping("doFindZTreeNodes")
    public JsonResult doFindZTreeNodes() {
        return new JsonResult(sysDeptsService.findZTreeNodes());
    }

    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysDepts entity){
        sysDeptsService.updateObject(entity);
        return new JsonResult("update ok");
    }

    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysDepts entity){
        sysDeptsService.saveObject(entity);
        return new JsonResult("save ok");
    }
    @RequestMapping("doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(Integer id){
        sysDeptsService.deleteObject(id);
        return new JsonResult("delete ok");
    }
}