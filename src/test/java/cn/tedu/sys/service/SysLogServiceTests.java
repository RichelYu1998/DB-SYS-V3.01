package cn.tedu.sys.service;

import cn.tedu.common.exception.ServiceException;
import cn.tedu.sys.entity.PageObject;
import cn.tedu.sys.entity.SysLogs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysLogServiceTests {
    @Autowired
    private SysLogsService sysLogsService;
    @Test
    public void testFindPageObjects() throws ServiceException {
        PageObject<SysLogs> pageObject = sysLogsService.findPageObjects("admin", 1);
        System.out.println(pageObject);
    }
}
