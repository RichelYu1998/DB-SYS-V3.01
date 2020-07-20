package cn.tedu.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志(SysLogs)实体类
 *
 * @author makejava
 * @since 2020-07-10 16:56:43
 */
@Data
public class SysLogs implements Serializable {
    //将对象序列化时会将id作为版本写入到字节中
    //将字节反序列化会从字节中提取版本号然后进行比对，一致则进行处理
    private static final long serialVersionUID = 334918830373580962L;
    private Integer id;
    //用户名
    private String username;
    //用户操作
    private String operation;
    //请求方法
    private String method;
    //请求参数
    private String params;
    //执行时长(毫秒)
    private Long time;
    //IP地址
    private String ip;
    //创建时间
    private Date createdTime;

}