package cn.tedu.sys.service;

import cn.tedu.sys.entity.PageObject;
import cn.tedu.sys.entity.SysLogs;

import java.util.List;

/**
 * 系统日志(SysLogs)表服务接口
 *
 * @author makejava
 * @since 2020-07-10 16:38:11
 */
public interface SysLogsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysLogs queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysLogs> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysLogs 实例对象
     * @return 实例对象
     */
    SysLogs insert(SysLogs sysLogs);

    /**
     * 修改数据
     *
     * @param sysLogs 实例对象
     * @return 实例对象
     */
   /* SysLogs update(SysLogs sysLogs);*/

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
    /**
     * @param username 基于条件查询时的参数名
     * @param pageCurrent 当前的页码值
     * @return 当前页记录+分页信息
     */
    PageObject<SysLogs> findPageObjects(
            String username,
            Integer pageCurrent
    );
    /*
    * 基于多个 id 进行日志删除
    * */
    int deleteObjects(Integer... ids);
    /*
    * 保存日志
    * */
    void saveObject(SysLogs entity);
}