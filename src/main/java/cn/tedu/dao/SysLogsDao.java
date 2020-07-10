package cn.tedu.dao;

import cn.tedu.entity.SysLogs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统日志(SysLogs)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-10 16:38:11
 */
@Mapper
public interface SysLogsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysLogs queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysLogs> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysLogs 实例对象
     * @return 对象列表
     */
    List<SysLogs> queryAll(SysLogs sysLogs);

    /**
     * 新增数据
     *
     * @param sysLogs 实例对象
     * @return 影响行数
     */
    int insert(SysLogs sysLogs);

    /**
     * 修改数据
     *
     * @param sysLogs 实例对象
     * @return 影响行数
     */
    int update(SysLogs sysLogs);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}