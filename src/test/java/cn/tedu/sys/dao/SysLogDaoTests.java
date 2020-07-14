package cn.tedu.sys.dao;

import cn.tedu.sys.entity.SysLogs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysLogDaoTests {
    @Autowired
    private SysLogsDao sysLogsDao;
    @Test
    public void testGetRowCount() {
        int rows=sysLogsDao.getRowCount("admin");
        System.out.println("rows="+rows);
    }
    @Test
    public void testFindPageObjects() {
        List<SysLogs> list=
                sysLogsDao.findPageObjects("admin", 0, 3);
        for(SysLogs log:list) {
            System.out.println(log);
        }
    }
}
