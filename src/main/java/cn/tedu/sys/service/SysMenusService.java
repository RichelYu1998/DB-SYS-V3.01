package cn.tedu.sys.service;

import cn.tedu.common.vo.Node;
import cn.tedu.common.vo.SysUserMenuVo;
import cn.tedu.sys.entity.SysMenus;

import java.util.List;
import java.util.Map;

/**
 * 资源管理(SysMenus)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 16:38:12
 */
public interface SysMenusService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysMenus queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysMenus> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysMenus 实例对象
     * @return 实例对象
     */
    SysMenus insert(SysMenus sysMenus);

    /**
     * 修改数据
     *
     * @param sysMenus 实例对象
     * @return 实例对象
     */
    SysMenus update(SysMenus sysMenus);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    /*
     * 菜单记录查询
     * */
    List<Map<String, Object>> findObjects();
    /*
    * 基于 id 进行菜单删除
    * */
    int deleteObject(Integer id);
    /*
    * 查询菜单信息
    * */
    List<Node> findZtreeMenuNodes();
    /*
    * 保存菜单对象
    * */
    int saveObject(SysMenus entity);
    /*
    * 更新菜单
    * */
    int updateObject(SysMenus entity);
    List<SysUserMenuVo> findUserMenusByUserId(Integer id);
}