package cn.tedu.service;

import cn.tedu.entity.SysUserRoles;

import java.util.List;

/**
 * 用户与角色对应关系(SysUserRoles)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 14:34:36
 */
public interface SysUserRolesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRoles queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUserRoles> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUserRoles 实例对象
     * @return 实例对象
     */
    SysUserRoles insert(SysUserRoles sysUserRoles);

    /**
     * 修改数据
     *
     * @param sysUserRoles 实例对象
     * @return 实例对象
     */
    SysUserRoles update(SysUserRoles sysUserRoles);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}