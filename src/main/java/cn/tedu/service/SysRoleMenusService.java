package cn.tedu.service;

import cn.tedu.entity.SysRoleMenus;

import java.util.List;

/**
 * 角色与菜单对应关系(SysRoleMenus)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 14:34:32
 */
public interface SysRoleMenusService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRoleMenus queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysRoleMenus> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysRoleMenus 实例对象
     * @return 实例对象
     */
    SysRoleMenus insert(SysRoleMenus sysRoleMenus);

    /**
     * 修改数据
     *
     * @param sysRoleMenus 实例对象
     * @return 实例对象
     */
    SysRoleMenus update(SysRoleMenus sysRoleMenus);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}