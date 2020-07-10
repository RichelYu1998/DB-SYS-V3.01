package cn.tedu.dao;

import cn.tedu.entity.SysDepts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门管理(SysDepts)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 16:38:05
 */
@Mapper
public interface SysDeptsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysDepts queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysDepts> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysDepts 实例对象
     * @return 对象列表
     */
    List<SysDepts> queryAll(SysDepts sysDepts);

    /**
     * 新增数据
     *
     * @param sysDepts 实例对象
     * @return 影响行数
     */
    int insert(SysDepts sysDepts);

    /**
     * 修改数据
     *
     * @param sysDepts 实例对象
     * @return 影响行数
     */
    int update(SysDepts sysDepts);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}