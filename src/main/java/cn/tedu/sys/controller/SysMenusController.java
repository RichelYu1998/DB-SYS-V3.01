package cn.tedu.sys.controller;

import cn.tedu.common.vo.JsonResult;
import cn.tedu.sys.entity.SysMenus;
import cn.tedu.sys.service.SysMenusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 资源管理(SysMenus)表控制层
 *
 * @author makejava
 * @since 2020-07-10 16:38:16
 */
@RestController
@RequestMapping("/menu/")
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
    /*
     * 菜单记录查询
     * */
    @RequestMapping("doFindObjects")
    public JsonResult doFindObjects(){
        return new JsonResult(sysMenusService.findObjects());
    }
    /*
    * 删除业务
    * */
    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        sysMenusService.deleteObject(id);
        return new JsonResult("delete ok");
    }
    /*
     * 查询菜单信息
     * */
    @RequestMapping("doFindZtreeMenuNodes")
    public JsonResult doFindZtreeMenuNodes(){
        return new JsonResult(sysMenusService.findZtreeMenuNodes());
    }
    /*
    * 保存菜单数据请求
    * */
    @RequestMapping("doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(SysMenus entity){
        sysMenusService.saveObject(entity);
        return new JsonResult("save ok");
    }
    /*
    * 保存菜单数据请求
    * */
    @RequestMapping("doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(SysMenus entity){
        sysMenusService.updateObject(entity);
        return new JsonResult("update ok");
    }
}