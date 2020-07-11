package cn.tedu.service.impl;

import cn.tedu.common.exception.ServiceException;
import cn.tedu.dao.SysLogsDao;
import cn.tedu.entity.PageObject;
import cn.tedu.entity.SysLogs;
import cn.tedu.service.SysLogsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统日志(SysLogs)表服务实现类
 *
 * @author makejava
 * @since 2020-07-10 16:38:11
 */
@Service("sysLogsService")
public class SysLogsServiceImpl implements SysLogsService {
    @Resource
    private SysLogsDao sysLogsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysLogs queryById(Long id) {
        return this.sysLogsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysLogs> queryAllByLimit(int offset, int limit) {
        return this.sysLogsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysLogs 实例对象
     * @return 实例对象
     */
    @Override
    public SysLogs insert(SysLogs sysLogs) {
        this.sysLogsDao.insert(sysLogs);
        return sysLogs;
    }

    /**
     * 修改数据
     *
     * @param sysLogs 实例对象
     * @return 实例对象
     */
    @Override
    public SysLogs update(SysLogs sysLogs) {
        this.sysLogsDao.update(sysLogs);
        return this.queryById(sysLogs.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysLogsDao.deleteById(id) > 0;
    }

    @Override
    public PageObject<SysLogs> findPageObjects(String username, Integer pageCurrent) {
        //1.验证参数合法性
        //1.1 验证 pageCurrent 的合法性，
        //不合法抛出 IllegalArgumentException 异常
        if (pageCurrent == null || pageCurrent < 1)
            throw new IllegalArgumentException("当前页码不正确");
        //2.基于条件查询总记录数
        //2.1) 执行查询
        int rowCount = sysLogsDao.getRowCount(username);
        //2.2) 验证查询结果，假如结果为 0 不再执行如下操作
        if (rowCount == 0)
            throw new ServiceException("系统没有查到对应记录");
        //3.基于条件查询当前页记录(pageSize 定义为 2)
        //3.1)定义 pageSize
        int pageSize = 2;
        //3.2)计算 startIndex
        int startIndex = (pageCurrent - 1) * pageSize;
        //3.3)执行当前数据的查询操作
        List<SysLogs> records =
                sysLogsDao.findPageObjects(username, startIndex, pageSize);
        //4.对分页信息以及当前页记录进行封装
        //4.1)构建 PageObject 对象
        PageObject<SysLogs> pageObject = new PageObject<>();
        //4.2)封装数据
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(rowCount);
        pageObject.setRecords(records);
        pageObject.setPageCount((rowCount - 1) / pageSize + 1);
        //5.返回封装结果。
        return pageObject;
    }

    @Override
    public int deleteObjects(Integer... ids) {
        //1.判定参数合法性
        if (ids == null || ids.length == 0)
            throw new IllegalArgumentException("请选择一个");
        //2.执行删除操作
        int rows;
        try {
            rows = sysLogsDao.deleteObjects(ids);
        } catch (Throwable e) {
            e.printStackTrace();
            //发出报警信息(例如给运维人员发短信)
            throw new ServiceException("系统故障，正在恢复中...");
        }
        //4.对结果进行验证if(rows==0)
        //throw new ServiceException("记录可能已经不存在");
        ////5.返回结果
        return rows;
    }
}
