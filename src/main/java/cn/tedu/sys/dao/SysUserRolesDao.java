package cn.tedu.sys.dao;

import cn.tedu.sys.entity.SysUserRoles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与角色对应关系(SysUserRoles)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 16:38:23
 */
@Mapper
public interface SysUserRolesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserRoles queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUserRoles> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUserRoles 实例对象
     * @return 对象列表
     */
    List<SysUserRoles> queryAll(SysUserRoles sysUserRoles);

    /**
     * 新增数据
     *
     * @param sysUserRoles 实例对象
     * @return 影响行数
     */
    int insert(SysUserRoles sysUserRoles);

    /**
     * 修改数据
     *
     * @param sysUserRoles 实例对象
     * @return 影响行数
     */
    int update(SysUserRoles sysUserRoles);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}