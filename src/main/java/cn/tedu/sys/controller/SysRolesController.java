package cn.tedu.sys.controller;

import cn.tedu.common.vo.JsonResult;
import cn.tedu.sys.entity.SysRoles;
import cn.tedu.sys.service.SysRolesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    /*
     * 菜单查询处理
     * */
    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(String name,Integer pageCurrent){
        return new JsonResult(
                sysRolesService.findPageObjects(name,
                        pageCurrent));

    }
    /*
    * 删除业务
    * */
    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        sysRolesService.deleteObject(id);
        return new JsonResult("delete ok");
    }
    /*
    * 保存角色数据请求
    * */
    @RequestMapping("doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(SysRoles entity,Integer[] menuIds){
        sysRolesService.saveObject(entity,menuIds);
        return new JsonResult("save ok");
    }
    /*
    * 基于角色 ID 查询角色
    * */
    @RequestMapping("doFindObjectById")
    public JsonResult doFindObjectById(Integer id){
        return new JsonResult(sysRolesService.findObjectById(id));
    }
    /*
    * 更新角色
    * */
    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysRoles entity, Integer[] menuIds){
        sysRolesService.updateObject(entity,menuIds);
        return new JsonResult("update ok");
    }
}