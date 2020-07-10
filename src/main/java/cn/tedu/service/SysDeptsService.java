package cn.tedu.service;

import cn.tedu.entity.SysDepts;
import java.util.List;

/**
 * 部门管理(SysDepts)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 11:52:04
 */
public interface SysDeptsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysDepts queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysDepts> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysDepts 实例对象
     * @return 实例对象
     */
    SysDepts insert(SysDepts sysDepts);

    /**
     * 修改数据
     *
     * @param sysDepts 实例对象
     * @return 实例对象
     */
    SysDepts update(SysDepts sysDepts);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}