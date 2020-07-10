package cn.tedu.service;

import cn.tedu.entity.SysUsers;
import java.util.List;

/**
 * 系统用户(SysUsers)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 11:52:06
 */
public interface SysUsersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUsers queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUsers> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUsers 实例对象
     * @return 实例对象
     */
    SysUsers insert(SysUsers sysUsers);

    /**
     * 修改数据
     *
     * @param sysUsers 实例对象
     * @return 实例对象
     */
    SysUsers update(SysUsers sysUsers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}