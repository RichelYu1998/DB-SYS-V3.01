package cn.tedu.sys.dao;

import cn.tedu.common.vo.Node;
import cn.tedu.common.vo.SysUserMenuVo;
import cn.tedu.sys.entity.SysMenus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 资源管理(SysMenus)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 16:38:12
 */
@Mapper
public interface SysMenusDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysMenus queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysMenus> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysMenus 实例对象
     * @return 对象列表
     */
    List<SysMenus> queryAll(SysMenus sysMenus);

    /**
     * 新增数据
     *
     * @param sysMenus 实例对象
     * @return 影响行数
     */
    int insert(SysMenus sysMenus);

    /**
     * 修改数据
     *
     * @param sysMenus 实例对象
     * @return 影响行数
     */
    int update(SysMenus sysMenus);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /*
     * 查询
     * */
    List<Map<String, Object>> findObjects();
    /*
    * 删除
    * */
    int getChildCount(Integer id);
    /*
    * 基于菜单 id 删除菜单记录
    * */
    int deleteObject(Integer id);
    /*
    * 查询上级菜单
    * */
    List<Node> findZtreeMenuNodes();
    /*
    * 数据持久化
    * */
    int insertObject(SysMenus entity);
    /*
    * 数据更新
    * */
    int updateObject(SysMenus entity);
    /*
    * 基于菜单 id 查找权限标识
    * */
    List<String> findPermissions(@Param("menuIds") Integer[] menuIds);
    /**
     * 基于菜单获取菜单信息
     * @param menuIds
     * @return
     */
    List<SysUserMenuVo> findMenusByIds(
            @Param("menuIds")List<Integer> menuIds);
}