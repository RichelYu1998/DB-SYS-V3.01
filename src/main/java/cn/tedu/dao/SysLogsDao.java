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
    /**
     * @param username 查询条件(例如查询哪个用户的日志信息)
     * @return 总记录数(基于这个结果可以计算总页数)
     */
    int getRowCount(@Param("username")String username);
    /**
     * @param username 查询条件(例如查询哪个用户的日志信息)
     * @param startIndex 当前页的起始位置
     * @param pageSize 当前页的页面大小
     * @return 当前页的日志记录信息
     * 数据库中每条日志信息封装到一个 SysLog 对象中
     */
    List<SysLogs> findPageObjects(
            @Param("username")String username,
            @Param("startIndex")Integer startIndex,
            @Param("pageSize") int pageSize
    );
    /*
    * 加基于 id 执行日志删除
    * */
    int deleteObjects(@Param("ids")Integer...ids);
    /*
    * 日志信息持久化
    * */
    int insertObject(SysLogs entity);
}