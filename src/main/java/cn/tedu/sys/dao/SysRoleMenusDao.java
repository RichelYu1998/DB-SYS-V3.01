package cn.tedu.sys.dao;

import cn.tedu.sys.entity.SysRoleMenus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色与菜单对应关系(SysRoleMenus)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 16:38:17
 */
@Mapper
public interface SysRoleMenusDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRoleMenus queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysRoleMenus> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRoleMenus 实例对象
     * @return 对象列表
     */
    List<SysRoleMenus> queryAll(SysRoleMenus sysRoleMenus);

    /**
     * 新增数据
     *
     * @param sysRoleMenus 实例对象
     * @return 影响行数
     */
    int insert(SysRoleMenus sysRoleMenus);

    /**
     * 修改数据
     *
     * @param sysRoleMenus 实例对象
     * @return 影响行数
     */
    int update(SysRoleMenus sysRoleMenus);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
    /*
     *基于菜单 id 删除关系数据
     * */
    int deleteObjectsByMenuId(Integer menuId);
}