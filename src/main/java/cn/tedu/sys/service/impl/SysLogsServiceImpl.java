package cn.tedu.sys.service.impl;

import cn.tedu.common.exception.ServiceException;
import cn.tedu.sys.dao.SysLogsDao;
import cn.tedu.sys.entity.PageObject;
import cn.tedu.sys.entity.SysLogs;
import cn.tedu.sys.service.SysLogsService;
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
    public PageObject<SysLogs> findPageObjects(String username,
                                              Integer pageCurrent) {
        //1.参数校验
        //说明：对于所有的检验，非空校验一定要放在第一步。
        if(pageCurrent==null||pageCurrent<1)
            throw new IllegalArgumentException("页码值不正确");
        //2.查询总记录数
        int rowCount=sysLogsDao.getRowCount(username);
        if(rowCount==0)
            throw new ServiceException("没有找到对应的记录");
        //3.查询当前页要呈现的记录
        int pageSize=3;//页面大小(每页最多呈现多少条记录)
        int startIndex=(pageCurrent-1)*pageSize;//计算起始位置
        List<SysLogs> records=
                sysLogsDao.findPageObjects(username, startIndex, pageSize);
        //4.对数据进行封装并返回
        //说明：构建对象时，参数的顺序是怎样的要结合你的构造方法的定义
        return new PageObject<>(pageCurrent, pageSize,rowCount, records);
    }

    @Override
    public int deleteObjects(Integer... ids) {
        //1.参数校验
        if(ids==null||ids.length==0)
            throw new IllegalArgumentException("参数值无效");
        //2.基于id删除记录
        int rows=-1;
        try {
            rows=sysLogsDao.deleteObjects(ids);//后续对这样的方法调用也要进行异常监控
        }catch(Throwable e) {
            //报警，给运维人员发短信。
            throw new ServiceException("远端数据访问暂时维护中");
        }
        //3.结果校验并返回
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        return rows;
    }
}
