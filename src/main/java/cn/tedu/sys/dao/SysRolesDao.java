package cn.tedu.sys.dao;

import cn.tedu.common.vo.CheckBox;
import cn.tedu.common.vo.SysRoleMenuVo;
import cn.tedu.sys.entity.SysRoles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色(SysRoles)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 16:38:21
 */
@Mapper
public interface SysRolesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRoles queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysRoles> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRoles 实例对象
     * @return 对象列表
     */
    List<SysRoles> queryAll(SysRoles sysRoles);

    /**
     * 新增数据
     *
     * @param sysRoles 实例对象
     * @return 影响行数
     */
    int insert(SysRoles sysRoles);

    /**
     * 修改数据
     *
     * @param sysRoles 实例对象
     * @return 影响行数
     */
    int update(SysRoles sysRoles);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
    /*
     *按条件统计记录总数
     * */
    int getRowCount(@Param("name") String name);
    List<SysRoles> findPageObjects(
            @Param("name")String name,
            @Param("startIndex")Integer startIndex,
            @Param("pageSize")Integer pageSize
    );
    /*
    * 基于菜单 id 删除角色记录
    * */
    int deleteObject(Integer id);
    /*
    * 数据持久化
    * */
    int insertObject(SysRoles entity);
    /*
    * 数据持久化
    * */
    SysRoleMenuVo findObjectById(Integer id);
    /*
    * 数据更新
    * */
    int updateObject(SysRoles entity);
    /*
    * 查询角色
    * */
    List<CheckBox> findObjects();
}