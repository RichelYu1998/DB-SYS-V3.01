package cn.tedu.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户(SysUsers)实体类
 *
 * @author makejava
 * @since 2020-07-10 16:56:45
 */
@Data
public class SysUsers implements Serializable {
    private static final long serialVersionUID = 325594955302027637L;
    private Integer id;
    private String username;
    private String password;//md5
    private String salt;
    private String email;
    private String mobile;
    private Integer valid=1;
    private Integer deptId;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;

}